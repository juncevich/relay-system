import {Badge, Descriptions, Modal} from 'antd';
import {Relay} from '../../types/relay.types';
import {StorageInfo} from '../../api/StorageService';
import {getVerificationStatus, VERIFICATION_STATUS} from '../../utils/verificationStatus';

interface RelayInfoModalProps {
    relay: Relay | null;
    storages: StorageInfo[];
    onClose: () => void;
}

function RelayInfoModal({relay, storages, onClose}: RelayInfoModalProps) {
    const status = getVerificationStatus(relay?.verificationDate);
    const {label, color} = VERIFICATION_STATUS[status];

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
