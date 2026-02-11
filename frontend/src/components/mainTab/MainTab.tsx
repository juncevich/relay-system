import {Fragment, useMemo} from 'react';

import {Alert, Breadcrumb, Col, Layout, Menu, Row, Space, Spin, Tabs} from 'antd';
import RelayCard from '../relay/RelayCard';
import Relay from '../../models/Relay';
import useRelayData from '../../hooks/useRelayData';
import './RelayContent.css';

const {Content, Sider} = Layout;

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
                    <div><RelayCard relay={relay} /></div>
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
            rows.push(<Fragment key={i}>{row}</Fragment>);
        }
    }
    return rows.length > 0 ? <>{rows}</> : <Alert message="Нет реле для отображения" type="info" />;
};

function MainTab() {
    // Use custom hook for data fetching (React best practice)
    const { relays, stations, loading, error } = useRelayData();

    // Memoize tab content to prevent unnecessary recalculations
    const tab1Content = useMemo(
        () => generateTabContent(relays.slice(0, 24), 3),
        [relays]
    );
    const tab2Content = useMemo(
        () => generateTabContent(relays.slice(24, 32), 1),
        [relays]
    );
    const tab3Content = useMemo(
        () => generateTabContent(relays.slice(32, 48), 2),
        [relays]
    );

    // Memoize menu items to prevent re-creation on each render
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

    const tabItems = useMemo(() => [
        {
            key: '1',
            label: `Склад 1 (${Math.min(relays.length, 24)})`,
            children: tab1Content,
        },
        {
            key: '2',
            label: `Склад 2 (${Math.min(Math.max(0, relays.length - 24), 8)})`,
            children: tab2Content,
        },
        {
            key: '3',
            label: `Склад 3 (${Math.min(Math.max(0, relays.length - 32), 16)})`,
            children: tab3Content,
        },
    ], [relays.length, tab1Content, tab2Content, tab3Content]);

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
                            defaultSelectedKeys={stations.length > 0 ? [`station-${stations[0].id}`] : []}
                            defaultOpenKeys={['stations']}
                            items={sideMenuItems}
                        />
                    )}
                </Sider>
                <Content className="main-inner-content">
                    {loading ? (
                        <Spin tip="Загрузка реле..." size="large">
                            <div className="loading-container"/>
                        </Spin>
                    ) : error ? (
                        <Alert
                            message="Ошибка загрузки данных"
                            description={error}
                            type="error"
                            showIcon
                        />
                    ) : (
                        <Space>
                            <Tabs
                                tabPosition="top"
                                centered
                                items={tabItems}
                            />
                        </Space>
                    )}
                </Content>
            </Layout>
        </Content>
    );
}

export default MainTab;
