import {Card} from 'antd';
import {CloseCircleFilled, DeleteOutlined, EditOutlined} from '@ant-design/icons';
import {Relay} from '../../types/relay.types';

const { Meta } = Card;

const DEFAULT_RELAY_IMAGE = '/images/relay-default.svg';

function formatVerificationDate(date?: string): string {
    if (!date) return 'Не проверено';
    return new Date(date).toLocaleDateString('ru-RU');
}

interface RelayCardProps {
    relay?: Relay;
    onEdit?: (relay: Relay) => void;
    onDelete?: (relay: Relay) => void;
}

function RelayCard({relay, onEdit, onDelete}: RelayCardProps) {
    return (
        <div className="relay-card-container">
            <Card
                cover={
                    <img
                        alt={relay?.serialNumber ? `Реле ${relay.serialNumber}` : 'Изображение реле'}
                        src={DEFAULT_RELAY_IMAGE}
                    />
                }
                actions={[
                    <EditOutlined
                        key="edit"
                        aria-label="Редактировать"
                        onClick={() => relay && onEdit?.(relay)}
                    />,
                    <DeleteOutlined
                        key="delete"
                        aria-label="Удалить"
                        onClick={() => relay && onDelete?.(relay)}
                    />,
                ]}
            >
                <Meta
                    avatar={<CloseCircleFilled style={{color: '#08c'}} aria-label="Статус реле" />}
                    title={relay?.serialNumber}
                />
                <table>
                    <tbody>
                        <tr>
                            <td>Дата проверки</td>
                            <td>{relay ? formatVerificationDate(relay.verificationDate) : ''}</td>
                        </tr>
                    </tbody>
                </table>
            </Card>
        </div>
    );
}

export default RelayCard;
