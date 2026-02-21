import {Badge, Descriptions, Modal} from 'antd';
import {Relay} from '../../types/relay.types';
import {StorageInfo} from '../../api/StorageService';

interface RelayInfoModalProps {
    relay: Relay | null;
    storages: StorageInfo[];
    onClose: () => void;
}

function getVerificationStatus(date?: string): 'ok' | 'warning' | 'overdue' | 'unknown' {
    if (!date) return 'unknown';
    const diffDays = (Date.now() - new Date(date).getTime()) / 86_400_000;
    if (diffDays > 365) return 'overdue';
    if (diffDays > 270) return 'warning';
    return 'ok';
}

const STATUS: Record<string, { label: string; color: string }> = {
    ok: {label: 'Проверено', color: 'green'},
    warning: {label: 'Скоро проверка', color: 'gold'},
    overdue: {label: 'Просрочено', color: 'red'},
    unknown: {label: 'Не проверено', color: 'default'},
};

function RelayInfoModal({relay, storages, onClose}: RelayInfoModalProps) {
    const status = getVerificationStatus(relay?.verificationDate);
    const {label, color} = STATUS[status];

    const storageName = relay?.storageId !== undefined
        ? (storages.find(s => s.id === relay.storageId)?.name ?? '—')
        : '—';

    const createdAtFormatted = relay?.createdAt
        ? new Date(relay.createdAt).toLocaleDateString('ru-RU')
        : '—';

    const verificationDateFormatted = relay?.verificationDate
        ? new Date(relay.verificationDate).toLocaleDateString('ru-RU')
        : 'Нет';

    return (
        <Modal
            title={`Реле ${relay?.serialNumber ?? ''}`}
            open={relay !== null}
            onCancel={onClose}
            footer={null}
            destroyOnHidden
        >
            {relay && (
                <Descriptions column={1} bordered size="small">
                    <Descriptions.Item label="Серийный номер">
                        {relay.serialNumber}
                    </Descriptions.Item>
                    <Descriptions.Item label="Тип реле">
                        {relay.relayType ?? 'Не указан'}
                    </Descriptions.Item>
                    <Descriptions.Item label="Статус">
                        <Badge color={color} text={label}/>
                    </Descriptions.Item>
                    <Descriptions.Item label="Дата производства">
                        {createdAtFormatted}
                    </Descriptions.Item>
                    <Descriptions.Item label="Дата проверки">
                        {verificationDateFormatted}
                    </Descriptions.Item>
                    <Descriptions.Item label="Место">
                        {relay.placeNumber ?? '—'}
                    </Descriptions.Item>
                    <Descriptions.Item label="Хранилище">
                        {storageName}
                    </Descriptions.Item>
                </Descriptions>
            )}
        </Modal>
    );
}

export default RelayInfoModal;
