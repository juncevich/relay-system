import {Layout, Menu} from 'antd';
import {Outlet, useLocation, useNavigate} from 'react-router-dom';
import ErrorBoundary from '../errorBoundary/ErrorBoundary';
import '../mainTab/RelayContent.css';

const {Header, Footer} = Layout;

const navItems = [
    {key: 'main', label: 'Главная'},
    {key: 'relays', label: 'Реле'},
    {key: 'stations', label: 'Станции'},
];

const keyToPath: Record<string, string> = {
    main: '/',
    relays: '/relays',
    stations: '/stations',
};

const pathToKey: Record<string, string> = {
    '/': 'main',
    '/relays': 'relays',
    '/stations': 'stations',
};

function AppLayout() {
    const navigate = useNavigate();
    const location = useLocation();

    const selectedKey = pathToKey[location.pathname] ?? 'main';

    const handleMenuClick = ({key}: {key: string}) => {
        const path = keyToPath[key];
        if (path) {
            navigate(path);
        }
    };

    return (
        <ErrorBoundary>
            <Layout>
                <Header className="header">
                    <div className="logo"/>
                    <Menu
                        theme="dark"
                        mode="horizontal"
                        selectedKeys={[selectedKey]}
                        items={navItems}
                        onClick={handleMenuClick}
                    />
                </Header>
                <Outlet/>
                <Footer className="main-footer">Ant Design ©2018 Created by Ant UED</Footer>
            </Layout>
        </ErrorBoundary>
    );
}

export default AppLayout;
