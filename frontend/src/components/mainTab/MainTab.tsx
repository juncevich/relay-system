import React from 'react';
import {LaptopOutlined, NotificationOutlined, UserOutlined} from '@ant-design/icons';

import {Breadcrumb, Layout, Menu} from 'antd';
import RelayCard from '../relay/RelayCard';

const {SubMenu} = Menu;
const {Header, Content, Footer, Sider} = Layout;

class MainTab extends React.Component<any, any> {

    render() {
        const relays = [<RelayCard/>, <RelayCard/>, <RelayCard/>, <RelayCard/>];
        return (
            <Layout>
                <Header className="header">
                    <div className="logo"/>
                    <Menu theme="dark" mode="horizontal" defaultSelectedKeys={['2']}>
                        <Menu.Item key="1">nav 1</Menu.Item>
                        <Menu.Item key="2">nav 2</Menu.Item>
                        <Menu.Item key="3">nav 3</Menu.Item>
                    </Menu>
                </Header>
                <Content style={{padding: '0 50px'}}>
                    <Breadcrumb style={{margin: '16px 0'}}>
                        <Breadcrumb.Item>Home</Breadcrumb.Item>
                        <Breadcrumb.Item>List</Breadcrumb.Item>
                        <Breadcrumb.Item>App</Breadcrumb.Item>
                    </Breadcrumb>
                    <Layout className="site-layout-background" style={{padding: '24px 0'}}>
                        <Sider className="site-layout-background" width={"auto"} >
                            <Menu
                                mode="inline"
                                defaultSelectedKeys={['1']}
                                defaultOpenKeys={['sub1']}
                                style={{height: '100%'}}
                            >
                                <SubMenu key="sub1" title="Свердловский участок" >
                                    <Menu.Item key="1">Березит</Menu.Item>
                                    <Menu.Item key="2">Кедровка</Menu.Item>
                                    <Menu.Item key="3">Монетная</Menu.Item>
                                    <Menu.Item key="4">Копалуха</Menu.Item>
                                </SubMenu>
                            </Menu>
                        </Sider>
                        <Content style={{padding: '0 24px', minHeight: 280, }}>
                            {relays}
                        </Content>
                    </Layout>
                </Content>
                <Footer style={{textAlign: 'center'}}>Ant Design ©2018 Created by Ant UED</Footer>
            </Layout>
        );


    }
}

export default MainTab;
