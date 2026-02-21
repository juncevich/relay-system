import {Layout, Menu} from 'antd';
import {Outlet, useLocation, useNavigate} from 'react-router';
import ErrorBoundary from '../errorBoundary/ErrorBoundary';
import '../mainTab/RelayContent.css';
import './AppLayout.css';

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
            <Layout style={{minHeight: '100vh', background: 'var(--bg)'}}>
                <Header className="app-header">
                    <div className="app-brand" onClick={() => navigate('/')} style={{cursor: 'pointer'}}>
                        <div className="app-brand__icon">
                            <svg width="30" height="30" viewBox="0 0 30 30" fill="none" aria-hidden="true">
                                <rect x="1" y="1" width="28" height="28" rx="5" stroke="#00c8d4" strokeWidth="1.5"/>
                                <rect x="5.5" y="8" width="19" height="3.5" rx="1.75" fill="#00c8d4"/>
                                <rect x="5.5" y="14" width="19" height="3.5" rx="1.75" fill="#00c8d4" opacity="0.55"/>
                                <rect x="5.5" y="20" width="11" height="3.5" rx="1.75" fill="#00c8d4" opacity="0.25"/>
                                <circle cx="23.5" cy="21.5" r="2.8" fill="#4ade80"/>
                            </svg>
                        </div>
                        <div className="app-brand__text">
                            <span className="app-brand__name">Relay</span>
                            <span className="app-brand__sub">System</span>
                        </div>
                    </div>

                    <Menu
                        theme="dark"
                        mode="horizontal"
                        selectedKeys={[selectedKey]}
                        items={navItems}
                        onClick={handleMenuClick}
                        className="app-nav"
                    />

                    <div className="app-status">
                        <span className="app-status__dot"/>
                        <span className="app-status__label">Онлайн</span>
                    </div>
                </Header>

                <Outlet/>

                <Footer className="app-footer main-footer">
                    Relay System · ©2025
                </Footer>
            </Layout>
        </ErrorBoundary>
    );
}

export default AppLayout;
