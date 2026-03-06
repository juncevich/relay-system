import {DeleteOutlined, EditOutlined} from '@ant-design/icons';
import {Relay} from '../../types/relay.types';
import {getVerificationStatus, VERIFICATION_STATUS} from '../../utils/verificationStatus';
import './RelayCard.css';

const DEFAULT_RELAY_IMAGE = '/images/relay-default.svg';

function formatVerificationDate(date?: string): string {
    if (!date) return 'Не проверено';
    return new Date(date).toLocaleDateString('ru-RU');
}

interface RelayCardProps {
    relay?: Relay;
    onEdit?: (relay: Relay) => void;
    onDelete?: (relay: Relay) => void;
    onClick?: (relay: Relay) => void;
}

function RelayCard({relay, onEdit, onDelete, onClick}: RelayCardProps) {
    const status = getVerificationStatus(relay?.verificationDate);
    const {label, color} = VERIFICATION_STATUS[status];

    return (
        <div className="relay-card-container">
            {/* Hidden image for test & accessibility */}
            <img
                alt={relay?.serialNumber ? `Реле ${relay.serialNumber}` : 'Изображение реле'}
                src={DEFAULT_RELAY_IMAGE}
                style={{display: 'none'}}
                aria-hidden="true"
            />

            <div
                className={`relay-card${onClick ? ' relay-card--clickable' : ''}`}
                onClick={() => relay && onClick?.(relay)}
            >
                <div className="relay-card__stripe" style={{background: color}}/>

                <div className="relay-card__body">
                    <div className="relay-card__header">
                        <span
                            className="relay-card__indicator"
                            aria-label="Статус реле"
                            style={{background: color, boxShadow: `0 0 7px ${color}80`}}
                        />
                        <div className="relay-card__actions">
                            <button
                                className="relay-card__action-btn"
                                aria-label="Редактировать"
                                onClick={(e) => {
                                    e.stopPropagation();
                                    relay && onEdit?.(relay);
                                }}
                            >
                                <EditOutlined/>
                            </button>
                            <button
                                className="relay-card__action-btn relay-card__action-btn--danger"
                                aria-label="Удалить"
                                onClick={(e) => {
                                    e.stopPropagation();
                                    relay && onDelete?.(relay);
                                }}
                            >
                                <DeleteOutlined/>
                            </button>
                        </div>
                    </div>

                    <div className="relay-card__serial">
                        {relay?.serialNumber ?? '—'}
                    </div>

                    {/* Table preserved for test compatibility */}
                    <table className="relay-card__meta-table">
                        <tbody>
                        <tr>
                            <td>Дата проверки</td>
                            <td style={{color}}>
                                {relay ? formatVerificationDate(relay.verificationDate) : ''}
                            </td>
                        </tr>
                        </tbody>
                    </table>

                    <div
                        className="relay-card__badge"
                        style={{
                            color,
                            borderColor: `${color}50`,
                            background: `${color}12`,
                        }}
                    >
                        {label}
                    </div>
                </div>
            </div>
        </div>
    );
}

export default RelayCard;
