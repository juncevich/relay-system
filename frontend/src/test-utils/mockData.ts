import { Relay as BackendRelay, StationResponse, GetAllRelaysResponse, GetAllStationsResponse } from '../types/relay.types';
import Relay from '../models/Relay';

export const mockBackendRelay: BackendRelay = {
    id: 1,
    serialNumber: 'REL-001',
    relayType: 'НШ-12',
    createdAt: '2024-01-15T10:00:00Z',
    lastCheckDate: '2024-02-01T10:00:00Z',
    placeNumber: 1,
    storageId: 1,
    shelfId: 1
};

export const mockBackendRelay2: BackendRelay = {
    id: 2,
    serialNumber: 'REL-002',
    relayType: 'НШ-13',
    createdAt: '2024-01-16T10:00:00Z',
    lastCheckDate: '2024-02-02T10:00:00Z',
    placeNumber: 2,
    storageId: 1,
    shelfId: 1
};

export const mockBackendRelayWithoutCheckDate: BackendRelay = {
    id: 3,
    serialNumber: 'REL-003',
    relayType: 'НШ-14',
    createdAt: '2024-01-17T10:00:00Z',
    placeNumber: 3,
    storageId: 2,
    shelfId: 2
};

export const mockLegacyRelay = new Relay(
    1,
    'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
    'REL-001',
    '01.02.2024'
);

export const mockLegacyRelay2 = new Relay(
    2,
    'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
    'REL-002',
    '02.02.2024'
);

export const mockStation: StationResponse = {
    id: 1,
    name: 'Свердловский участок'
};

export const mockStation2: StationResponse = {
    id: 2,
    name: 'Московский участок'
};

export const mockGetAllRelaysResponse: GetAllRelaysResponse = {
    content: [mockBackendRelay, mockBackendRelay2, mockBackendRelayWithoutCheckDate],
    totalElements: 3,
    totalPages: 1,
    size: 10,
    number: 0
};

export const mockGetAllStationsResponse: GetAllStationsResponse = {
    content: [mockStation, mockStation2],
    totalElements: 2,
    totalPages: 1
};

export const createMockRelays = (count: number): Relay[] => {
    return Array.from({ length: count }, (_, i) => new Relay(
        i + 1,
        'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
        `REL-${String(i + 1).padStart(3, '0')}`,
        new Date(2024, 1, i + 1).toLocaleDateString('ru-RU')
    ));
};
