import type {Mocked} from 'vitest';
import {renderHook, waitFor} from '@testing-library/react';
import useRelayData from './useRelayData';
import RelayService from '../api/RelayService';
import LocationService from '../api/LocationService';
import StorageService from '../api/StorageService';
import {
    mockBackendRelay,
    mockGetAllRelaysResponse,
    mockGetAllStationsResponse,
    mockStation,
    mockStorages
} from '../test-utils/mockData';

// Mock the services
vi.mock('../api/RelayService');
vi.mock('../api/LocationService');
vi.mock('../api/StorageService');

const mockRelayService = RelayService as Mocked<typeof RelayService>;
const mockLocationService = LocationService as Mocked<typeof LocationService>;
const mockStorageService = StorageService as Mocked<typeof StorageService>;

describe('useRelayData', () => {
    beforeEach(() => {
        vi.clearAllMocks();
    });

    it('should start with loading state', () => {
        mockRelayService.getAll.mockReturnValue(new Promise(() => {}));
        mockLocationService.getAllStations.mockReturnValue(new Promise(() => {}));
        mockStorageService.getAllStorages.mockReturnValue(new Promise(() => {}));

        const { result } = renderHook(() => useRelayData());

        expect(result.current.loading).toBe(true);
        expect(result.current.error).toBeNull();
        expect(result.current.relays).toEqual([]);
        expect(result.current.stations).toEqual([]);
        expect(result.current.storages).toEqual([]);
    });

    it('should fetch and return relays, stations and storages', async () => {
        mockRelayService.getAll.mockResolvedValue({ data: mockGetAllRelaysResponse } as never);
        mockLocationService.getAllStations.mockResolvedValue({ data: mockGetAllStationsResponse } as never);
        mockStorageService.getAllStorages.mockResolvedValue(mockStorages);

        const { result } = renderHook(() => useRelayData());

        await waitFor(() => {
            expect(result.current.loading).toBe(false);
        });

        expect(result.current.error).toBeNull();
        expect(result.current.relays).toHaveLength(3);
        expect(result.current.relays[0].id).toBe(mockBackendRelay.id);
        expect(result.current.stations).toHaveLength(2);
        expect(result.current.stations[0].name).toBe(mockStation.name);
        expect(result.current.storages).toHaveLength(2);
    });

    it('should handle API errors', async () => {
        const errorMessage = 'Network error';
        mockRelayService.getAll.mockRejectedValue(new Error(errorMessage));
        mockLocationService.getAllStations.mockRejectedValue(new Error(errorMessage));
        mockStorageService.getAllStorages.mockRejectedValue(new Error(errorMessage));

        const { result } = renderHook(() => useRelayData());

        await waitFor(() => {
            expect(result.current.loading).toBe(false);
        });

        expect(result.current.error).toBe(errorMessage);
        expect(result.current.relays).toEqual([]);
    });

    it('should handle API error with response message', async () => {
        const apiError = {
            response: {
                data: {
                    message: 'Server error message'
                }
            }
        };
        mockRelayService.getAll.mockRejectedValue(apiError);
        mockLocationService.getAllStations.mockRejectedValue(apiError);
        mockStorageService.getAllStorages.mockRejectedValue(apiError);

        const { result } = renderHook(() => useRelayData());

        await waitFor(() => {
            expect(result.current.loading).toBe(false);
        });

        expect(result.current.error).toBe('Server error message');
    });

    it('should use custom pagination options', async () => {
        mockRelayService.getAll.mockResolvedValue({ data: mockGetAllRelaysResponse } as never);
        mockLocationService.getAllStations.mockResolvedValue({ data: mockGetAllStationsResponse } as never);
        mockStorageService.getAllStorages.mockResolvedValue(mockStorages);

        renderHook(() => useRelayData({ relayPageSize: 100, stationPageSize: 20 }));

        await waitFor(() => {
            expect(mockRelayService.getAll).toHaveBeenCalledWith({ page: 0, size: 100 });
            expect(mockLocationService.getAllStations).toHaveBeenCalledWith({ page: 0, size: 20 });
        });
    });
});
