import React from 'react';

import {Breadcrumb, Col, Layout, Menu, Row, Space, Tabs} from 'antd';
import RelayCard from '../relay/RelayCard';
import Relay from '../../models/Relay';
import './RelayContent.css';

const {Header, Content, Footer, Sider} = Layout;

function MainTab() {
    const relayModel = new Relay(
        'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
        'НМШ-400',
        '01.02.2003'
    );

    // Helper function to render a row of relay cards
    const renderRelayRow = (count: number = 8) => (
        <Row gutter={[8, 8]}>
            {Array.from({ length: count }).map((_, index) => (
                <Col key={index} className="gutter-row" span={3}>
                    <div><RelayCard relay={relayModel}/></div>
                </Col>
            ))}
        </Row>
    );

    // Tab 1 content: 3 rows of 8 cards
    const tab1Content = (
        <>
            {renderRelayRow(8)}
            {renderRelayRow(8)}
            {renderRelayRow(8)}
        </>
    );

    // Tab 2 content: 1 row of 8 cards
    const tab2Content = renderRelayRow(8);

    // Tab 3 content: 2 rows of 8 cards
    const tab3Content = (
        <>
            {renderRelayRow(8)}
            {renderRelayRow(8)}
        </>
    );

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
                        <Menu
                            mode="inline"
                            defaultSelectedKeys={['1']}
                            defaultOpenKeys={['sub1']}
                            items={[
                                {
                                    key: 'sub1',
                                    label: 'Свердловский участок',
                                    children: [
                                        { key: '1', label: 'Березит' },
                                        { key: '2', label: 'Кедровка' },
                                        { key: '3', label: 'Монетная' },
                                        { key: '4', label: 'Копалуха' },
                                    ],
                                },
                            ]}
                        />
                    </Sider>
                    <Content style={{padding: '0 24px', minHeight: 280, maxWidth: '100%'}}>
                        <Space>
                            <Tabs
                                tabPosition="top"
                                centered
                                items={[
                                    {
                                        key: '1',
                                        label: 'Tab 1',
                                        children: tab1Content,
                                    },
                                    {
                                        key: '2',
                                        label: 'Tab 2',
                                        children: tab2Content,
                                    },
                                    {
                                        key: '3',
                                        label: 'Tab 3',
                                        children: tab3Content,
                                    },
                                ]}
                            />
                        </Space>
                    </Content>
                </Layout>
            </Content>
            <Footer style={{textAlign: 'center'}}>Ant Design ©2018 Created by Ant UED</Footer>
        </Layout>
    );
}

// class MainTab extends React.Component<any, any> {
//
//     render() {
//         // const relays = [<RelayCard/>, <RelayCard/>, <RelayCard/>, <RelayCard/>, <RelayCard/>, <RelayCard/>, <RelayCard/>, <RelayCard/>];
//         const relay = <RelayCard/>;
//         return (
//             <Layout>
//                 <Header className="header">
//                     <div className="logo"/>
//                     <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
//                         <Menu.Item key="1">nav 1</Menu.Item>
//                         <Menu.Item key="2">nav 2</Menu.Item>
//                         <Menu.Item key="3">nav 3</Menu.Item>
//                     </Menu>
//                 </Header>
//                 <Content style={{padding: '0 50px'}}>
//                     <Breadcrumb style={{margin: '16px 0'}}>
//                         <Breadcrumb.Item>Home</Breadcrumb.Item>
//                         <Breadcrumb.Item>List</Breadcrumb.Item>
//                         <Breadcrumb.Item>App</Breadcrumb.Item>
//                     </Breadcrumb>
//                     <Layout className="site-layout-background" style={{padding: '24px 0'}}>
//                         <Sider className="site-layout-background" width={'auto'}>
//                             <Menu
//                                 mode="inline"
//                                 defaultSelectedKeys={['1']}
//                                 defaultOpenKeys={['sub1']}
//                                 // style={{height: '100%'}}
//                             >
//                                 <SubMenu key="sub1" title="Свердловский участок">
//                                     <Menu.Item key="1">Березит</Menu.Item>
//                                     <Menu.Item key="2">Кедровка</Menu.Item>
//                                     <Menu.Item key="3">Монетная</Menu.Item>
//                                     <Menu.Item key="4">Копалуха</Menu.Item>
//                                 </SubMenu>
//                             </Menu>
//                         </Sider>
//                         <Content style={{padding: '0 24px', minHeight: 280, maxWidth: '100%'}}>
//
//                             <Space>
//                                 <Tabs tabPosition="top" centered>
//                                     <TabPane tab="Tab 1" key="1">
//                                         <Row gutter={[8, 8]}>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                         </Row>
//
//                                         <Row gutter={[8, 8]}>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                         </Row>
//
//                                         <Row gutter={[8, 8]}>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                         </Row>
//                                     </TabPane>
//                                     <TabPane tab="Tab 2" key="2">
//                                         <Row gutter={[8, 8]}>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                         </Row>
//                                     </TabPane>
//                                     <TabPane tab="Tab 3" key="3">
//                                         <Row gutter={[8, 8]}>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                         </Row>
//                                         <Row gutter={[8, 8]}>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                             <Col className="gutter-row" span={3}>
//                                                 <div><RelayCard/></div>
//                                             </Col>
//                                         </Row>
//                                     </TabPane>
//                                 </Tabs>
//                             </Space>
//
//                         </Content>
//                     </Layout>
//                 </Content>
//                 <Footer style={{textAlign: 'center'}}>Ant Design ©2018 Created by Ant UED</Footer>
//             </Layout>
//         );
//
//
//     }
// }

export default MainTab;
