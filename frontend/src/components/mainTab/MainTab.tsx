import React, { useState, useEffect } from 'react';

import {Breadcrumb, Col, Layout, Menu, Row, Space, Tabs, Spin, Alert} from 'antd';
import RelayCard from '../relay/RelayCard';
import Relay from '../../models/Relay';
import RelayService from '../../api/RelayService';
import LocationService from '../../api/LocationService';
import { Relay as BackendRelay, StationResponse } from '../../types/relay.types';
import './RelayContent.css';

const {Header, Content, Footer, Sider} = Layout;

interface MainTabState {
    relays: Relay[];
    stations: StationResponse[];
    loading: boolean;
    error: string | null;
}

function MainTab() {
    const [state, setState] = useState<MainTabState>({
        relays: [],
        stations: [],
        loading: true,
        error: null
    });

    // Fetch data from backend on component mount
    useEffect(() => {
        let ignore = false;

        const fetchData = async () => {
            try {
                // Fetch relays and stations in parallel
                const [relaysResponse, stationsResponse] = await Promise.all([
                    RelayService.getAll({ page: 0, size: 50 }),
                    LocationService.getAllStations({ page: 0, size: 10 })
                ]);

                // Prevent state updates if component unmounted
                if (!ignore) {
                    // Convert backend relays to legacy Relay model
                    const relays = relaysResponse.data.content.map((backendRelay: BackendRelay) =>
                        Relay.fromBackendRelay(backendRelay)
                    );

                    setState({
                        relays,
                        stations: stationsResponse.data.content,
                        loading: false,
                        error: null
                    });
                }
            } catch (error: unknown) {
                if (!ignore) {
                    const errorMessage = error instanceof Error
                        ? error.message
                        : 'Failed to fetch data from backend';

                    const apiError = error as { response?: { data?: { message?: string } } };
                    const finalErrorMessage = apiError.response?.data?.message || errorMessage;

                    if (process.env.NODE_ENV === 'development') {
                        console.error('Error fetching data:', error);
                    }

                    setState(prev => ({
                        ...prev,
                        loading: false,
                        error: finalErrorMessage
                    }));
                }
            }
        };

        fetchData();

        return () => {
            ignore = true;
        };
    }, []);

    // Helper function to render a row of relay cards
    const renderRelayRow = (relays: Relay[], startIndex: number, count: number = 8) => {
        const relaysToShow = relays.slice(startIndex, startIndex + count);

        if (relaysToShow.length === 0) {
            return null;
        }

        return (
            <Row gutter={[8, 8]}>
                {relaysToShow.map((relay) => (
                    <Col key={relay.id} className="gutter-row" span={3}>
                        <div><RelayCard relay={relay}/></div>
                    </Col>
                ))}
            </Row>
        );
    };

    // Generate tab content based on actual relay data
    const generateTabContent = (relays: Relay[], rowsPerTab: number) => {
        const rows = [];
        for (let i = 0; i < rowsPerTab; i++) {
            const row = renderRelayRow(relays, i * 8, 8);
            if (row) {
                rows.push(<React.Fragment key={i}>{row}</React.Fragment>);
            }
        }
        return rows.length > 0 ? <>{rows}</> : <Alert message="Нет реле для отображения" type="info" />;
    };

    // Tab content
    const tab1Content = generateTabContent(state.relays.slice(0, 24), 3); // First 24 relays (3 rows)
    const tab2Content = generateTabContent(state.relays.slice(24, 32), 1); // Next 8 relays (1 row)
    const tab3Content = generateTabContent(state.relays.slice(32, 48), 2); // Next 16 relays (2 rows)

    return (
        <Layout>
            <Header className="header">
                <div className="logo"/>
                <Menu
                    theme="dark"
                    mode="horizontal"
                    defaultSelectedKeys={['2']}
                    items={[
                        { key: '1', label: 'nav 1' },
                        { key: '2', label: 'nav 2' },
                        { key: '3', label: 'nav 3' },
                    ]}
                />
            </Header>
            <Content style={{padding: '0 50px'}}>
                <Breadcrumb
                    style={{margin: '16px 0'}}
                    items={[
                        { title: 'Home' },
                        { title: 'List' },
                        { title: 'App' },
                    ]}
                />
                <Layout className="site-layout-background" style={{padding: '24px 0'}}>
                    <Sider className="site-layout-background" width={'auto'}>
                        {state.loading ? (
                            <div style={{ padding: '20px', textAlign: 'center' }}>
                                <Spin />
                            </div>
                        ) : (
                            <Menu
                                mode="inline"
                                defaultSelectedKeys={state.stations.length > 0 ? [`station-${state.stations[0].id}`] : []}
                                defaultOpenKeys={['stations']}
                                items={[
                                    {
                                        key: 'stations',
                                        label: 'Станции',
                                        children: state.stations.map(station => ({
                                            key: `station-${station.id}`,
                                            label: station.name
                                        }))
                                    },
                                ]}
                            />
                        )}
                    </Sider>
                    <Content style={{padding: '0 24px', minHeight: 280, maxWidth: '100%'}}>
                        {state.loading ? (
                            <Spin tip="Загрузка реле..." size="large">
                                <div style={{ minHeight: 200 }} />
                            </Spin>
                        ) : state.error ? (
                            <Alert
                                message="Ошибка загрузки данных"
                                description={state.error}
                                type="error"
                                showIcon
                            />
                        ) : (
                            <Space>
                                <Tabs
                                    tabPosition="top"
                                    centered
                                    items={[
                                        {
                                            key: '1',
                                            label: `Склад 1 (${Math.min(state.relays.length, 24)})`,
                                            children: tab1Content,
                                        },
                                        {
                                            key: '2',
                                            label: `Склад 2 (${Math.min(Math.max(0, state.relays.length - 24), 8)})`,
                                            children: tab2Content,
                                        },
                                        {
                                            key: '3',
                                            label: `Склад 3 (${Math.min(Math.max(0, state.relays.length - 32), 16)})`,
                                            children: tab3Content,
                                        },
                                    ]}
                                />
                            </Space>
                        )}
                    </Content>
                </Layout>
            </Content>
            <Footer style={{textAlign: 'center'}}>Ant Design ©2018 Created by Ant UED</Footer>
        </Layout>
    );
}

export default MainTab;
