import {useCallback} from 'react';
import {useQuery} from '@tanstack/react-query';
import {LocationService, RelayService, StorageService} from '../services';
import {StorageInfo} from '../api/StorageService';
import {CrossingResponse, getApiErrorMessage, Relay, StationResponse, TrackPointResponse} from '../types/relay.types';

export interface RelayDataState {
    relays: Relay[];
    stations: StationResponse[];
    trackPoints: TrackPointResponse[];
    crossings: CrossingResponse[];
    storages: StorageInfo[];
    loading: boolean;
    error: string | null;
}

interface UseRelayDataOptions {
    relayPageSize?: number;
    stationPageSize?: number;
}

/**
 * Custom hook for fetching relay, station, and storage data from backend.
 * Follows React 19 best practices with race condition prevention.
 *
 * Set VITE_USE_MOCK_DATA=true to use local JSON mock data instead of the backend API.
 */
export interface UseRelayDataResult extends RelayDataState {
    refetch: () => void;
}

async function fetchRelayData(relayPageSize: number, stationPageSize: number) {
    const [relaysResponse, stationsResponse, trackPointsResponse, crossingsResponse, storages] = await Promise.all([
        RelayService.getAll({page: 0, size: relayPageSize}),
        LocationService.getAllStations({page: 0, size: stationPageSize}),
        LocationService.getAllTrackPoints({page: 0, size: stationPageSize}),
        LocationService.getAllCrossings({page: 0, size: stationPageSize}),
        StorageService.getAllStorages()
    ]);

    return {
        relays: relaysResponse.data.items,
        stations: stationsResponse.data.items,
        trackPoints: trackPointsResponse.data.items,
        crossings: crossingsResponse.data.items,
        storages,
    };
}

export function useRelayData(options: UseRelayDataOptions = {}): UseRelayDataResult {
    const {relayPageSize = 50, stationPageSize = 10} = options;
    const {data, isPending, error, refetch: queryRefetch} = useQuery({
        queryKey: ['relay-data', relayPageSize, stationPageSize],
        queryFn: () => fetchRelayData(relayPageSize, stationPageSize),
    });

    const refetch = useCallback(() => {
        void queryRefetch();
    }, [queryRefetch]);

    return {
        relays: data?.relays ?? [],
        stations: data?.stations ?? [],
        trackPoints: data?.trackPoints ?? [],
        crossings: data?.crossings ?? [],
        storages: data?.storages ?? [],
        loading: isPending,
        error: error ? getApiErrorMessage(error, 'Failed to fetch data from backend') : null,
        refetch
    };
}

export default useRelayData;
