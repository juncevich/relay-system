import {App as AntApp, ConfigProvider} from 'antd';
import MainPage from './pages/MainPage';

function App() {
    return (
        <ConfigProvider theme={{token: {colorPrimary: '#1677ff'}}}>
            <AntApp>
                <MainPage/>
            </AntApp>
        </ConfigProvider>
    );
}

export default App;
