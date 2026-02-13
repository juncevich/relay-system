import {useEffect, useState} from 'react';
import Relay from '../models/Relay';
import RealRelayService from '../api/RelayService';
import RealLocationService from '../api/LocationService';
import RealStorageService, {StorageInfo} from '../api/StorageService';
import MockRelayService from '../mock-data/MockRelayService';
import MockLocationService from '../mock-data/MockLocationService';
import MockStorageService from '../mock-data/MockStorageService';
import {
    getApiErrorMessage,
    Relay as BackendRelay,
    StationResponse,
    TrackPointResponse,
    CrossingResponse
} from '../types/relay.types';

const useMockData = import.meta.env.VITE_USE_MOCK_DATA === 'true';
const RelayService = useMockData ? MockRelayService : RealRelayService;
const LocationService = useMockData ? MockLocationService : RealLocationService;
const StorageService = useMockData ? MockStorageService : RealStorageService;

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
export function useRelayData(options: UseRelayDataOptions = {}): RelayDataState {
    const { relayPageSize = 50, stationPageSize = 10 } = options;

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
                    const relays = relaysResponse.data.content.map((backendRelay: BackendRelay) =>
                        Relay.fromBackendRelay(backendRelay)
                    );

                    setState({
                        relays,
                        stations: stationsResponse.data.content,
                        trackPoints: trackPointsResponse.data.content,
                        crossings: crossingsResponse.data.content,
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
    }, [relayPageSize, stationPageSize]);

    return state;
}

export default useRelayData;
