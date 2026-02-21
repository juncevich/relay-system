import {
    CrossingResponse,
    GetAllCrossingsResponse,
    GetAllRelaysResponse,
    GetAllStationsResponse,
    GetAllTrackPointsResponse,
    Relay,
    StationResponse,
    TrackPointResponse
} from '../types/relay.types';
import {StorageInfo} from '../api/StorageService';

export const mockBackendRelay: Relay = {
    id: 301,
    serialNumber: 'НМШ-001',
    relayType: 'NMSH_400',
    createdAt: '2022-03-04T09:07:00+05:00',
    verificationDate: '2024-03-03T09:11:00+05:00',
    placeNumber: 1,
    storageId: 101,
    shelfId: 201
};

export const mockBackendRelay2: Relay = {
    id: 302,
    serialNumber: 'НМШ-002',
    relayType: 'NMSH_400',
    createdAt: '2022-03-07T10:14:00+05:00',
    verificationDate: '2024-03-05T10:22:00+05:00',
    placeNumber: 2,
    storageId: 101,
    shelfId: 201
};

export const mockBackendRelayWithoutCheckDate: Relay = {
    id: 303,
    serialNumber: 'РЭЛ-003',
    relayType: 'REL1_1600',
    createdAt: '2022-03-10T11:21:00+05:00',
    placeNumber: 3,
    storageId: 101,
    shelfId: 201
};

export const mockStation: StationResponse = {
    id: 1,
    name: 'Екатеринбург-Пасс.'
};

export const mockStation2: StationResponse = {
    id: 2,
    name: 'Первомайская'
};

export const mockGetAllRelaysResponse: GetAllRelaysResponse = {
    relays: [mockBackendRelay, mockBackendRelay2, mockBackendRelayWithoutCheckDate],
    totalElements: 3,
    totalPages: 1,
    size: 10,
    number: 0
};

export const mockGetAllStationsResponse: GetAllStationsResponse = {
    stations: [mockStation, mockStation2],
    totalElements: 2,
    totalPages: 1,
    size: 10,
    number: 0
};

export const mockTrackPoint: TrackPointResponse = {
    id: 7,
    name: 'Шарташ'
};

export const mockCrossing: CrossingResponse = {
    id: 11,
    name: 'Переезд 39 км'
};

export const mockGetAllTrackPointsResponse: GetAllTrackPointsResponse = {
    trackPoints: [mockTrackPoint],
    totalElements: 1,
    totalPages: 1,
    size: 10,
    number: 0
};

export const mockGetAllCrossingsResponse: GetAllCrossingsResponse = {
    crossings: [mockCrossing],
    totalElements: 1,
    totalPages: 1,
    size: 10,
    number: 0
};

export const mockStorages: StorageInfo[] = [
    {id: 101, name: 'Склад ШЧ Екатеринбург', locationId: 1},
    {id: 104, name: 'Релейный шкаф ст. Первомайская', locationId: 2},
];

export const createMockRelays = (count: number, storageId: number = 101): Relay[] => {
    return Array.from({length: count}, (_, i) => ({
        id: 301 + i,
        serialNumber: `НМШ-${String(i + 1).padStart(3, '0')}`,
        relayType: 'NMSH_400',
        createdAt: '2022-03-04T09:07:00+05:00',
        verificationDate: new Date(2024, 2, i + 3).toISOString(),
        placeNumber: i + 1,
        storageId,
        shelfId: 201
    }));
};

export const createMockStations = (count: number): StationResponse[] => {
    const stationNames = ['Екатеринбург-Пасс.', 'Первомайская', 'Монетная', 'Реж', 'Егоршино', 'Алапаевск'];
    return Array.from({ length: count }, (_, i) => ({
        id: i + 1,
        name: stationNames[i % stationNames.length]
    }));
};

export const createMockTrackPoints = (count: number): TrackPointResponse[] => {
    const names = ['Шарташ', 'Березит', 'Адуй', 'Костоусово'];
    return Array.from({ length: count }, (_, i) => ({
        id: 7 + i,
        name: names[i % names.length]
    }));
};

export const createMockCrossings = (count: number): CrossingResponse[] => {
    const names = ['Переезд 39 км', 'Переезд 85 км', 'Переезд 125 км'];
    return Array.from({ length: count }, (_, i) => ({
        id: 11 + i,
        name: names[i % names.length]
    }));
};
