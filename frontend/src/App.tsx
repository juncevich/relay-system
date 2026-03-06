import {lazy, Suspense} from 'react';
import {App as AntApp, ConfigProvider, theme} from 'antd';
import {BrowserRouter, Route, Routes} from 'react-router';
import {QueryClient, QueryClientProvider} from '@tanstack/react-query';

const AppLayout = lazy(() => import('./components/layout/AppLayout'));
const HomePage = lazy(() => import('./pages/HomePage'));
const MainPage = lazy(() => import('./pages/MainPage'));
const StationsPage = lazy(() => import('./pages/StationsPage'));
const NotFoundPage = lazy(() => import('./pages/NotFoundPage'));
const queryClient = new QueryClient({
    defaultOptions: {
        queries: {
            staleTime: 30_000,
            refetchOnWindowFocus: false,
            retry: 1,
        }
    }
});

function RouteFallback() {
    return (
        <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '60vh', color: '#8fa3c0'}}>
            Загрузка...
        </div>
    );
}

function App() {
    return (
        <QueryClientProvider client={queryClient}>
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
                        <Route
                            element={<Suspense fallback={<RouteFallback/>}><AppLayout/></Suspense>}
                        >
                            <Route
                                path="/"
                                element={<Suspense fallback={<RouteFallback/>}><HomePage/></Suspense>}
                            />
                            <Route
                                path="/relays"
                                element={<Suspense fallback={<RouteFallback/>}><MainPage/></Suspense>}
                            />
                            <Route
                                path="/stations"
                                element={<Suspense fallback={<RouteFallback/>}><StationsPage/></Suspense>}
                            />
                            <Route
                                path="*"
                                element={<Suspense fallback={<RouteFallback/>}><NotFoundPage/></Suspense>}
                            />
                        </Route>
                    </Routes>
                </BrowserRouter>
                </AntApp>
            </ConfigProvider>
        </QueryClientProvider>
    );
}

export default App;
