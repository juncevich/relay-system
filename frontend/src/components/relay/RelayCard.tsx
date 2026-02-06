import {Card} from 'antd';
import {CloseCircleFilled, EditOutlined, EllipsisOutlined, SettingOutlined} from '@ant-design/icons';
import './RelayCard.css';
import Relay from '../../models/Relay';

const { Meta } = Card;

interface RelayCardProps {
    relay?: Relay;
}

function RelayCard({relay}: RelayCardProps) {
    return (
        <div className="relay-card-container">
            <Card
                cover={
                    <img
                        alt={relay?.title ? `Реле ${relay.title}` : 'Изображение реле'}
                        src={relay?.imgUrl}
                    />
                }
                actions={[
                    <SettingOutlined key="setting" aria-label="Настройки" />,
                    <EditOutlined key="edit" aria-label="Редактировать" />,
                    <EllipsisOutlined key="ellipsis" aria-label="Дополнительные действия" />,
                ]}
            >
                <Meta
                    avatar={<CloseCircleFilled style={{color: '#08c'}} aria-label="Статус реле" />}
                    title={relay?.title}
                />
                <table>
                    <tbody>
                        <tr>
                            <td>Дата проверки</td>
                            <td>{relay?.checkingDate}</td>
                        </tr>
                    </tbody>
                </table>
            </Card>
        </div>
    );
}

export default RelayCard;
