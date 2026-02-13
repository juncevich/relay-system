import {Fragment, useCallback, useMemo, useState} from 'react';

import {Alert, Breadcrumb, Col, Layout, Menu, Row, Spin, Tabs} from 'antd';
import RelayCard from '../relay/RelayCard';
import Relay from '../../models/Relay';
import useRelayData from '../../hooks/useRelayData';
import {StorageInfo} from '../../api/StorageService';
import './RelayContent.css';

const {Content, Sider} = Layout;

const RELAYS_PER_ROW = 8;

// Helper function to render a row of relay cards
const renderRelayRow = (relays: Relay[], startIndex: number, count: number = RELAYS_PER_ROW) => {
    const relaysToShow = relays.slice(startIndex, startIndex + count);

    if (relaysToShow.length === 0) {
        return null;
    }

    return (
        <Row gutter={[8, 8]}>
            {relaysToShow.map((relay) => (
                <Col key={relay.id} className="gutter-row" span={3}>
                    <div><RelayCard relay={relay} /></div>
                </Col>
            ))}
        </Row>
    );
};

// Generate tab content for a given array of relays
const generateTabContent = (relays: Relay[]) => {
    const rowCount = Math.ceil(relays.length / RELAYS_PER_ROW);
    const rows = [];
    for (let i = 0; i < rowCount; i++) {
        const row = renderRelayRow(relays, i * RELAYS_PER_ROW, RELAYS_PER_ROW);
        if (row) {
            rows.push(<Fragment key={i}>{row}</Fragment>);
        }
    }
    return rows.length > 0 ? <>{rows}</> : <Alert title="Нет реле для отображения" type="info" />;
};

function MainTab() {
    const { relays, stations, storages, loading, error } = useRelayData({
        relayPageSize: 300,
        stationPageSize: 50
    });
    const [selectedStationId, setSelectedStationId] = useState<number | null>(null);

    // Build storageId → locationId map
    const storageToLocationMap = useMemo(() => {
        const map = new Map<number, number>();
        storages.forEach((s: StorageInfo) => map.set(s.id, s.locationId));
        return map;
    }, [storages]);

    // Build locationId → storages map for tab names
    const locationToStoragesMap = useMemo(() => {
        const map = new Map<number, StorageInfo[]>();
        storages.forEach((s: StorageInfo) => {
            const list = map.get(s.locationId) ?? [];
            list.push(s);
            map.set(s.locationId, list);
        });
        return map;
    }, [storages]);

    // Auto-select first station when data loads
    const activeStationId = selectedStationId ?? (stations.length > 0 ? stations[0].id : null);

    // Get storageIds that belong to the selected station
    const stationStorageIds = useMemo(() => {
        if (activeStationId === null) return new Set<number>();
        const stationStorages = locationToStoragesMap.get(activeStationId) ?? [];
        return new Set(stationStorages.map(s => s.id));
    }, [activeStationId, locationToStoragesMap]);

    // Filter relays by selected station
    const filteredRelays = useMemo(() => {
        if (activeStationId === null) return relays;
        return relays.filter(r => r.storageId !== undefined && stationStorageIds.has(r.storageId));
    }, [relays, activeStationId, stationStorageIds]);

    const handleStationSelect = useCallback((info: { key: string }) => {
        const match = info.key.match(/^station-(\d+)$/);
        if (match) {
            setSelectedStationId(Number(match[1]));
        }
    }, []);

    // Memoize menu items
    const sideMenuItems = useMemo(() => [
        {
            key: 'stations',
            label: 'Станции',
            children: stations.map(station => ({
                key: `station-${station.id}`,
                label: station.name
            }))
        },
    ], [stations]);

    // Build tabs grouped by storage at the selected station
    const tabItems = useMemo(() => {
        if (activeStationId === null) return [];

        const stationStorages = locationToStoragesMap.get(activeStationId) ?? [];

        if (stationStorages.length === 0) {
            return [{
                key: 'all',
                label: `Все реле (${filteredRelays.length})`,
                children: generateTabContent(filteredRelays)
            }];
        }

        return stationStorages.map(storage => {
            const storageRelays = filteredRelays.filter(r => r.storageId === storage.id);
            return {
                key: `storage-${storage.id}`,
                label: `${storage.name} (${storageRelays.length})`,
                children: generateTabContent(storageRelays)
            };
        });
    }, [activeStationId, locationToStoragesMap, filteredRelays]);

    return (
        <Content className="main-content">
            <Breadcrumb
                className="main-breadcrumb"
                items={[
                    {title: 'Home'},
                    {title: 'List'},
                    {title: 'App'},
                ]}
            />
            <Layout className="site-layout-background">
                <Sider className="site-layout-background" width={'auto'}>
                    {loading ? (
                        <div className="sider-loading">
                            <Spin/>
                        </div>
                    ) : (
                        <Menu
                            mode="inline"
                            selectedKeys={activeStationId !== null ? [`station-${activeStationId}`] : []}
                            defaultOpenKeys={['stations']}
                            items={sideMenuItems}
                            onClick={handleStationSelect}
                        />
                    )}
                </Sider>
                <Content className="main-inner-content">
                    {loading ? (
                        <Spin description="Загрузка реле..." size="large">
                            <div className="loading-container"/>
                        </Spin>
                    ) : error ? (
                        <Alert
                            title="Ошибка загрузки данных"
                            description={error}
                            type="error"
                            showIcon
                        />
                    ) : (
                        <Tabs
                            tabPlacement="top"
                            centered
                            items={tabItems}
                        />
                    )}
                </Content>
            </Layout>
        </Content>
    );
}

export default MainTab;
