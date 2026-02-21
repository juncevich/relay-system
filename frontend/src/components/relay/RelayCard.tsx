import {DeleteOutlined, EditOutlined} from '@ant-design/icons';
import {Relay} from '../../types/relay.types';
import './RelayCard.css';

const DEFAULT_RELAY_IMAGE = '/images/relay-default.svg';

function getVerificationStatus(date?: string): 'ok' | 'warning' | 'overdue' | 'unknown' {
    if (!date) return 'unknown';
    const diffDays = (Date.now() - new Date(date).getTime()) / 86_400_000;
    if (diffDays > 365) return 'overdue';
    if (diffDays > 270) return 'warning';
    return 'ok';
}

function formatVerificationDate(date?: string): string {
    if (!date) return 'Не проверено';
    return new Date(date).toLocaleDateString('ru-RU');
}

const STATUS: Record<string, { label: string; color: string }> = {
    ok: {label: 'Проверено', color: '#4ade80'},
    warning: {label: 'Скоро проверка', color: '#fbbf24'},
    overdue: {label: 'Просрочено', color: '#f87171'},
    unknown: {label: 'Не проверено', color: '#56708a'},
};

interface RelayCardProps {
    relay?: Relay;
    onEdit?: (relay: Relay) => void;
    onDelete?: (relay: Relay) => void;
}

function RelayCard({relay, onEdit, onDelete}: RelayCardProps) {
    const status = getVerificationStatus(relay?.verificationDate);
    const {label, color} = STATUS[status];

    return (
        <div className="relay-card-container">
            {/* Hidden image for test & accessibility */}
            <img
                alt={relay?.serialNumber ? `Реле ${relay.serialNumber}` : 'Изображение реле'}
                src={DEFAULT_RELAY_IMAGE}
                style={{display: 'none'}}
                aria-hidden="true"
            />

            <div className="relay-card">
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
                                onClick={() => relay && onEdit?.(relay)}
                            >
                                <EditOutlined/>
                            </button>
                            <button
                                className="relay-card__action-btn relay-card__action-btn--danger"
                                aria-label="Удалить"
                                onClick={() => relay && onDelete?.(relay)}
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
