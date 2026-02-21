import {App as AntApp, ConfigProvider, theme} from 'antd';
import {BrowserRouter, Route, Routes} from 'react-router';
import AppLayout from './components/layout/AppLayout';
import HomePage from './pages/HomePage';
import MainPage from './pages/MainPage';
import NotFoundPage from './pages/NotFoundPage';
import StationsPage from './pages/StationsPage';

function App() {
    return (
        <ConfigProvider
            theme={{
                algorithm: theme.darkAlgorithm,
                token: {
                    colorPrimary: '#00c8d4',
                    colorBgBase: '#0b0e14',
                    colorBgContainer: '#1c2537',
                    colorBgElevated: '#1c2537',
                    colorBgLayout: '#0b0e14',
                    colorBorder: '#253042',
                    colorBorderSecondary: '#1e2d3d',
                    colorText: '#dce6f5',
                    colorTextSecondary: '#8fa3c0',
                    colorTextTertiary: '#56708a',
                    colorSuccess: '#4ade80',
                    colorWarning: '#fbbf24',
                    colorError: '#f87171',
                    borderRadius: 6,
                    fontFamily: "'Exo 2', sans-serif",
                    fontSize: 14,
                },
                components: {
                    Layout: {
                        headerBg: '#090c12',
                        footerBg: '#090c12',
                        siderBg: '#131923',
                        bodyBg: '#0b0e14',
                    },
                    Menu: {
                        darkItemBg: 'transparent',
                        darkSubMenuItemBg: '#0d1520',
                        darkItemSelectedBg: 'rgba(0, 200, 212, 0.12)',
                        darkItemSelectedColor: '#00c8d4',
                        darkItemHoverBg: 'rgba(0, 200, 212, 0.06)',
                        darkItemHoverColor: '#4dd9e3',
                        itemBg: 'transparent',
                        subMenuItemBg: '#0d1520',
                        itemSelectedBg: 'rgba(0, 200, 212, 0.12)',
                        itemSelectedColor: '#00c8d4',
                        itemHoverBg: 'rgba(0, 200, 212, 0.06)',
                        fontSize: 13,
                    },
                    Button: {
                        colorPrimaryHover: '#4dd9e3',
                    },
                    Tabs: {
                        inkBarColor: '#00c8d4',
                        itemSelectedColor: '#00c8d4',
                        itemHoverColor: '#4dd9e3',
                    },
                    Modal: {
                        titleFontSize: 15,
                    },
                    Card: {
                        colorBgContainer: '#1c2537',
                    },
                },
            }}
        >
            <AntApp>
                <BrowserRouter>
                    <Routes>
                        <Route element={<AppLayout/>}>
                            <Route path="/" element={<HomePage/>}/>
                            <Route path="/relays" element={<MainPage/>}/>
                            <Route path="/stations" element={<StationsPage/>}/>
                            <Route path="*" element={<NotFoundPage/>}/>
                        </Route>
                    </Routes>
                </BrowserRouter>
            </AntApp>
        </ConfigProvider>
    );
}

export default App;
