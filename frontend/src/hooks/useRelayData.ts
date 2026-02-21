import {useCallback, useEffect, useState} from 'react';
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

export function useRelayData(options: UseRelayDataOptions = {}): UseRelayDataResult {
    const { relayPageSize = 50, stationPageSize = 10 } = options;
    const [fetchCounter, setFetchCounter] = useState(0);

    const [state, setState] = useState<RelayDataState>({
        relays: [],
        stations: [],
        trackPoints: [],
        crossings: [],
        storages: [],
        loading: true,
        error: null
    });

    useEffect(() => {
        let ignore = false;

        const fetchData = async () => {
            try {
                const [relaysResponse, stationsResponse, trackPointsResponse, crossingsResponse, storages] = await Promise.all([
                    RelayService.getAll({ page: 0, size: relayPageSize }),
                    LocationService.getAllStations({ page: 0, size: stationPageSize }),
                    LocationService.getAllTrackPoints({ page: 0, size: stationPageSize }),
                    LocationService.getAllCrossings({ page: 0, size: stationPageSize }),
                    StorageService.getAllStorages()
                ]);

                if (!ignore) {
                    setState({
                        relays: relaysResponse.data.relays,
                        stations: stationsResponse.data.stations,
                        trackPoints: trackPointsResponse.data.trackPoints,
                        crossings: crossingsResponse.data.crossings,
                        storages,
                        loading: false,
                        error: null
                    });
                }
            } catch (error: unknown) {
                if (!ignore) {
                    const errorMessage = getApiErrorMessage(error, 'Failed to fetch data from backend');

                    if (import.meta.env.DEV) {
                        console.error('Error fetching data:', error);
                    }

                    setState(prev => ({
                        ...prev,
                        loading: false,
                        error: errorMessage
                    }));
                }
            }
        };

        fetchData();

        return () => {
            ignore = true;
        };
    }, [relayPageSize, stationPageSize, fetchCounter]);

    const refetch = useCallback(() => {
        setFetchCounter(c => c + 1);
    }, []);

    return {...state, refetch};
}

export default useRelayData;
