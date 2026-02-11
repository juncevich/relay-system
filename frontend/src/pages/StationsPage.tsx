import {useEffect, useState} from 'react';
import {Button, Input, List, message, Modal, Space, Spin, Typography} from 'antd';
import {DeleteOutlined, EditOutlined, PlusOutlined} from '@ant-design/icons';
import RealLocationService from '../api/LocationService';
import MockLocationService from '../mock-data/MockLocationService';

const useMockData = import.meta.env.VITE_USE_MOCK_DATA === 'true';
const locationService = useMockData ? MockLocationService : RealLocationService;
import {getApiErrorMessage, StationResponse} from '../types/relay.types';

const {Title} = Typography;

function StationsPage() {
    const [stations, setStations] = useState<StationResponse[]>([]);
    const [loading, setLoading] = useState(false);
    const [modalOpen, setModalOpen] = useState(false);
    const [modalLoading, setModalLoading] = useState(false);
    const [editingStation, setEditingStation] = useState<StationResponse | null>(null);
    const [stationName, setStationName] = useState('');

    const fetchStations = async () => {
        setLoading(true);
        try {
            const response = await locationService.getAllStations({size: 100});
            setStations(response.data.content);
        } catch (error) {
            message.error(getApiErrorMessage(error, 'Не удалось загрузить станции'));
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchStations();
    }, []);

    const openAddModal = () => {
        setEditingStation(null);
        setStationName('');
        setModalOpen(true);
    };

    const openEditModal = (station: StationResponse) => {
        setEditingStation(station);
        setStationName(station.name);
        setModalOpen(true);
    };

    const handleSave = async () => {
        const trimmed = stationName.trim();
        if (!trimmed) {
            message.warning('Введите название станции');
            return;
        }
        setModalLoading(true);
        try {
            if (editingStation) {
                await locationService.updateStation(editingStation.id, trimmed);
                message.success('Станция обновлена');
            } else {
                await locationService.createStation(trimmed);
                message.success('Станция добавлена');
            }
            setModalOpen(false);
            fetchStations();
        } catch (error) {
            message.error(getApiErrorMessage(error, 'Не удалось сохранить станцию'));
        } finally {
            setModalLoading(false);
        }
    };

    const handleDelete = (station: StationResponse) => {
        Modal.confirm({
            title: 'Удалить станцию?',
            content: `Вы уверены, что хотите удалить станцию "${station.name}"?`,
            okText: 'Удалить',
            okType: 'danger',
            cancelText: 'Отмена',
            onOk: async () => {
                try {
                    await locationService.deleteStation(station.id);
                    message.success('Станция удалена');
                    fetchStations();
                } catch (error) {
                    message.error(getApiErrorMessage(error, 'Не удалось удалить станцию'));
                }
            },
        });
    };

    return (
        <div style={{padding: 24, maxWidth: 800}}>
            <Space style={{width: '100%', justifyContent: 'space-between', marginBottom: 16}}>
                <Title level={3} style={{margin: 0}}>Станции</Title>
                <Button type="primary" icon={<PlusOutlined/>} onClick={openAddModal}>
                    Добавить станцию
                </Button>
            </Space>

            <Spin spinning={loading}>
                <List
                    bordered
                    dataSource={stations}
                    locale={{emptyText: 'Нет станций'}}
                    renderItem={(station) => (
                        <List.Item
                            actions={[
                                <Button
                                    key="edit"
                                    type="link"
                                    icon={<EditOutlined/>}
                                    onClick={() => openEditModal(station)}
                                >
                                    Изменить
                                </Button>,
                                <Button
                                    key="delete"
                                    type="link"
                                    danger
                                    icon={<DeleteOutlined/>}
                                    onClick={() => handleDelete(station)}
                                >
                                    Удалить
                                </Button>,
                            ]}
                        >
                            {station.name}
                        </List.Item>
                    )}
                />
            </Spin>

            <Modal
                title={editingStation ? 'Изменить станцию' : 'Добавить станцию'}
                open={modalOpen}
                onOk={handleSave}
                onCancel={() => setModalOpen(false)}
                confirmLoading={modalLoading}
                okText="Сохранить"
                cancelText="Отмена"
            >
                <Input
                    placeholder="Название станции"
                    value={stationName}
                    onChange={(e) => setStationName(e.target.value)}
                    onPressEnter={handleSave}
                    autoFocus
                />
            </Modal>
        </div>
    );
}

export default StationsPage;
