import { Relay as BackendRelay, StationResponse, GetAllRelaysResponse, GetAllStationsResponse } from '../types/relay.types';
import Relay from '../models/Relay';

export const mockBackendRelay: BackendRelay = {
    id: 301,
    serialNumber: 'НМШ-001',
    relayType: 'NMSH_400',
    createdAt: '2022-03-04T09:07:00+05:00',
    lastCheckDate: '2024-03-03T09:11:00+05:00',
    placeNumber: 1,
    storageId: 101,
    shelfId: 201
};

export const mockBackendRelay2: BackendRelay = {
    id: 302,
    serialNumber: 'НМШ-002',
    relayType: 'NMSH_400',
    createdAt: '2022-03-07T10:14:00+05:00',
    lastCheckDate: '2024-03-05T10:22:00+05:00',
    placeNumber: 2,
    storageId: 101,
    shelfId: 201
};

export const mockBackendRelayWithoutCheckDate: BackendRelay = {
    id: 303,
    serialNumber: 'РЭЛ-003',
    relayType: 'REL1_1600',
    createdAt: '2022-03-10T11:21:00+05:00',
    placeNumber: 3,
    storageId: 101,
    shelfId: 201
};

export const mockLegacyRelay = new Relay(
    301,
    'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
    'НМШ-001',
    '03.03.2024'
);

export const mockLegacyRelay2 = new Relay(
    302,
    'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
    'НМШ-002',
    '05.03.2024'
);

export const mockStation: StationResponse = {
    id: 1,
    name: 'Екатеринбург-Пасс.'
};

export const mockStation2: StationResponse = {
    id: 2,
    name: 'Первомайская'
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
        301 + i,
        'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
        `НМШ-${String(i + 1).padStart(3, '0')}`,
        new Date(2024, 2, i + 3).toLocaleDateString('ru-RU')
    ));
};

export const createMockStations = (count: number): StationResponse[] => {
    const stationNames = ['Екатеринбург-Пасс.', 'Первомайская', 'Монетная', 'Реж', 'Егоршино', 'Алапаевск'];
    return Array.from({ length: count }, (_, i) => ({
        id: i + 1,
        name: stationNames[i % stationNames.length]
    }));
};
