import {App as AntApp, ConfigProvider} from 'antd';
import {BrowserRouter, Route, Routes} from 'react-router';
import AppLayout from './components/layout/AppLayout';
import HomePage from './pages/HomePage';
import MainPage from './pages/MainPage';
import NotFoundPage from './pages/NotFoundPage';
import StationsPage from './pages/StationsPage';

function App() {
    return (
        <ConfigProvider theme={{token: {colorPrimary: '#1677ff'}}}>
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
