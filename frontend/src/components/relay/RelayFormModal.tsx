import {useEffect} from 'react';
import {DatePicker, Form, Input, Modal, Select} from 'antd';
import dayjs from 'dayjs';
import {Relay} from '../../types/relay.types';
import {StorageInfo} from '../../api/StorageService';

interface RelayFormModalProps {
    open: boolean;
    onCancel: () => void;
    onSave: (values: RelayFormValues) => void;
    relay: Relay | null;
    storages: StorageInfo[];
    confirmLoading: boolean;
}

export interface RelayFormValues {
    serialNumber: string;
    dateOfManufacture?: string;
    storageId?: number;
    verificationDate?: string;
}

function RelayFormModal({open, onCancel, onSave, relay, storages, confirmLoading}: RelayFormModalProps) {
    const [form] = Form.useForm<RelayFormValues>();
    const isEdit = relay !== null;

    useEffect(() => {
        if (open) {
            if (relay) {
                form.setFieldsValue({
                    serialNumber: relay.serialNumber,
                    dateOfManufacture: undefined,
                    storageId: relay.storageId,
                    verificationDate: undefined,
                });
            } else {
                form.resetFields();
            }
        }
    }, [open, relay, form]);

    const handleOk = async () => {
        const values = await form.validateFields();
        onSave(values);
    };

    return (
        <Modal
            title={isEdit ? 'Редактировать реле' : 'Добавить реле'}
            open={open}
            onOk={handleOk}
            onCancel={onCancel}
            confirmLoading={confirmLoading}
            okText="Сохранить"
            cancelText="Отмена"
            destroyOnHidden
        >
            <Form form={form} layout="vertical">
                <Form.Item
                    name="serialNumber"
                    label="Серийный номер"
                    rules={[
                        {required: true, message: 'Введите серийный номер'},
                        {min: 5, message: 'Минимум 5 символов'},
                        {max: 10, message: 'Максимум 10 символов'},
                    ]}
                >
                    <Input placeholder="Серийный номер"/>
                </Form.Item>

                <Form.Item
                    name="dateOfManufacture"
                    label="Дата производства"
                    getValueFromEvent={(date: dayjs.Dayjs | null) => date?.format('YYYY-MM-DD')}
                    getValueProps={(value: string) => ({value: value ? dayjs(value) : undefined})}
                >
                    <DatePicker style={{width: '100%'}} placeholder="Выберите дату"/>
                </Form.Item>

                <Form.Item
                    name="storageId"
                    label="Хранилище"
                >
                    <Select
                        placeholder="Выберите хранилище"
                        allowClear
                        options={storages.map(s => ({value: s.id, label: s.name}))}
                    />
                </Form.Item>

                {isEdit && (
                    <Form.Item
                        name="verificationDate"
                        label="Дата проверки"
                        getValueFromEvent={(date: dayjs.Dayjs | null) => date?.format('YYYY-MM-DD')}
                        getValueProps={(value: string) => ({value: value ? dayjs(value) : undefined})}
                    >
                        <DatePicker style={{width: '100%'}} placeholder="Выберите дату"/>
                    </Form.Item>
                )}
            </Form>
        </Modal>
    );
}

export default RelayFormModal;
