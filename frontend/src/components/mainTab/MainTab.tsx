import {Fragment, useCallback, useMemo, useState} from 'react';

import {Alert, App, Breadcrumb, Button, Col, Layout, Menu, Row, Space, Spin, Tabs} from 'antd';
import {PlusOutlined} from '@ant-design/icons';
import RelayCard from '../relay/RelayCard';
import RelayFormModal, {RelayFormValues} from '../relay/RelayFormModal';
import useRelayData from '../../hooks/useRelayData';
import {getApiErrorMessage, Relay} from '../../types/relay.types';
import {RelayService} from '../../services';
import {StorageInfo} from '../../api/StorageService';
import './RelayContent.css';

const {Content, Sider} = Layout;

const RELAYS_PER_ROW = 8;

interface RelayCallbacks {
    onEdit: (relay: Relay) => void;
    onDelete: (relay: Relay) => void;
}

// Helper function to render a row of relay cards
const renderRelayRow = (relays: Relay[], startIndex: number, callbacks: RelayCallbacks, count: number = RELAYS_PER_ROW) => {
    const relaysToShow = relays.slice(startIndex, startIndex + count);

    if (relaysToShow.length === 0) {
        return null;
    }

    return (
        <Row gutter={[8, 8]}>
            {relaysToShow.map((relay) => (
                <Col key={relay.id} className="gutter-row" span={3}>
                    <div><RelayCard relay={relay} onEdit={callbacks.onEdit} onDelete={callbacks.onDelete}/></div>
                </Col>
            ))}
        </Row>
    );
};

// Generate tab content for a given array of relays
const generateTabContent = (relays: Relay[], callbacks: RelayCallbacks) => {
    const rowCount = Math.ceil(relays.length / RELAYS_PER_ROW);
    const rows = [];
    for (let i = 0; i < rowCount; i++) {
        const row = renderRelayRow(relays, i * RELAYS_PER_ROW, callbacks, RELAYS_PER_ROW);
        if (row) {
            rows.push(<Fragment key={i}>{row}</Fragment>);
        }
    }
    return rows.length > 0 ? <>{rows}</> : <Alert title="Нет реле для отображения" type="info" />;
};

function MainTab() {
    const {message, modal} = App.useApp();
    const {relays, stations, trackPoints, crossings, storages, loading, error, refetch} = useRelayData({
        relayPageSize: 300,
        stationPageSize: 50
    });
    const [selectedLocationId, setSelectedLocationId] = useState<number | null>(null);
    const [modalOpen, setModalOpen] = useState(false);
    const [modalLoading, setModalLoading] = useState(false);
    const [editingRelay, setEditingRelay] = useState<Relay | null>(null);

    const handleAdd = useCallback(() => {
        setEditingRelay(null);
        setModalOpen(true);
    }, []);

    const handleEdit = useCallback((relay: Relay) => {
        setEditingRelay(relay);
        setModalOpen(true);
    }, []);

    const handleDelete = useCallback((relay: Relay) => {
        modal.confirm({
            title: 'Удалить реле?',
            content: `Вы уверены, что хотите удалить реле "${relay.serialNumber}"?`,
            okText: 'Удалить',
            okType: 'danger',
            cancelText: 'Отмена',
            onOk: async () => {
                try {
                    await RelayService.delete(relay.id);
                    message.success('Реле удалено');
                    refetch();
                } catch (err) {
                    message.error(getApiErrorMessage(err, 'Не удалось удалить реле'));
                }
            },
        });
    }, [modal, message, refetch]);

    const handleSave = useCallback(async (values: RelayFormValues) => {
        setModalLoading(true);
        try {
            if (editingRelay) {
                await RelayService.update(editingRelay.id, {
                    serialNumber: values.serialNumber,
                    dateOfManufacture: values.dateOfManufacture,
                    verificationDate: values.verificationDate,
                    storageId: values.storageId,
                });
                message.success('Реле обновлено');
            } else {
                await RelayService.create({
                    serialNumber: values.serialNumber,
                    dateOfManufacture: values.dateOfManufacture ?? '',
                    storageId: values.storageId ?? 0,
                });
                message.success('Реле добавлено');
            }
            setModalOpen(false);
            refetch();
        } catch (err) {
            message.error(getApiErrorMessage(err, 'Не удалось сохранить реле'));
        } finally {
            setModalLoading(false);
        }
    }, [editingRelay, message, refetch]);

    const relayCallbacks: RelayCallbacks = useMemo(() => ({
        onEdit: handleEdit,
        onDelete: handleDelete,
    }), [handleEdit, handleDelete]);

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

    // Auto-select first location when data loads
    const allLocations = useMemo(() => [
        ...stations, ...trackPoints, ...crossings
    ], [stations, trackPoints, crossings]);

    const activeLocationId = selectedLocationId ?? (allLocations.length > 0 ? allLocations[0].id : null);

    // Get storageIds that belong to the selected location
    const locationStorageIds = useMemo(() => {
        if (activeLocationId === null) return new Set<number>();
        const locStorages = locationToStoragesMap.get(activeLocationId) ?? [];
        return new Set(locStorages.map(s => s.id));
    }, [activeLocationId, locationToStoragesMap]);

    // Filter relays by selected location
    const filteredRelays = useMemo(() => {
        if (activeLocationId === null) return relays;
        return relays.filter(r => r.storageId !== undefined && locationStorageIds.has(r.storageId));
    }, [relays, activeLocationId, locationStorageIds]);

    const selectedLocationName = useMemo(() => {
        if (activeLocationId === null) return null;
        const location = allLocations.find(l => l.id === activeLocationId);
        return location?.name ?? null;
    }, [activeLocationId, allLocations]);

    const breadcrumbItems = useMemo(() => {
        const items: { title: string }[] = [{title: 'Реле'}];
        if (selectedLocationName) {
            items.push({title: selectedLocationName});
        }
        return items;
    }, [selectedLocationName]);

    const handleLocationSelect = useCallback((info: { key: string }) => {
        const match = info.key.match(/^location-(\d+)$/);
        if (match) {
            setSelectedLocationId(Number(match[1]));
        }
    }, []);

    // Memoize menu items
    const sideMenuItems = useMemo(() => {
        const items = [];
        if (stations.length > 0) {
            items.push({
                key: 'stations',
                label: 'Станции',
                children: stations.map(s => ({
                    key: `location-${s.id}`,
                    label: s.name
                }))
            });
        }
        if (trackPoints.length > 0) {
            items.push({
                key: 'trackPoints',
                label: 'Перегоны',
                children: trackPoints.map(tp => ({
                    key: `location-${tp.id}`,
                    label: tp.name
                }))
            });
        }
        if (crossings.length > 0) {
            items.push({
                key: 'crossings',
                label: 'Переезды',
                children: crossings.map(c => ({
                    key: `location-${c.id}`,
                    label: c.name
                }))
            });
        }
        return items;
    }, [stations, trackPoints, crossings]);

    // Build tabs grouped by storage at the selected location
    const tabItems = useMemo(() => {
        if (activeLocationId === null) return [];

        const stationStorages = locationToStoragesMap.get(activeLocationId) ?? [];

        if (stationStorages.length === 0) {
            return [{
                key: 'all',
                label: `Все реле (${filteredRelays.length})`,
                children: generateTabContent(filteredRelays, relayCallbacks)
            }];
        }

        return stationStorages.map(storage => {
            const storageRelays = filteredRelays.filter(r => r.storageId === storage.id);
            return {
                key: `storage-${storage.id}`,
                label: `${storage.name} (${storageRelays.length})`,
                children: generateTabContent(storageRelays, relayCallbacks)
            };
        });
    }, [activeLocationId, locationToStoragesMap, filteredRelays, relayCallbacks]);

    return (
        <Content className="main-content">
            <Space style={{width: '100%', justifyContent: 'space-between'}}>
                <Breadcrumb
                    className="main-breadcrumb"
                    items={breadcrumbItems}
                />
                <Button type="primary" icon={<PlusOutlined/>} onClick={handleAdd}>
                    Добавить реле
                </Button>
            </Space>
            <Layout className="site-layout-background">
                <Sider className="site-layout-background" width={'auto'}>
                    {loading ? (
                        <div className="sider-loading">
                            <Spin/>
                        </div>
                    ) : (
                        <Menu
                            mode="inline"
                            selectedKeys={activeLocationId !== null ? [`location-${activeLocationId}`] : []}
                            defaultOpenKeys={['stations', 'trackPoints', 'crossings']}
                            items={sideMenuItems}
                            onClick={handleLocationSelect}
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
            <RelayFormModal
                open={modalOpen}
                onCancel={() => setModalOpen(false)}
                onSave={handleSave}
                relay={editingRelay}
                storages={storages}
                confirmLoading={modalLoading}
            />
        </Content>
    );
}

export default MainTab;
