import { useState, useEffect } from 'react';
import Relay from '../models/Relay';
import RelayService from '../api/RelayService';
import LocationService from '../api/LocationService';
import { Relay as BackendRelay, StationResponse, getApiErrorMessage } from '../types/relay.types';

export interface RelayDataState {
    relays: Relay[];
    stations: StationResponse[];
    loading: boolean;
    error: string | null;
}

interface UseRelayDataOptions {
    relayPageSize?: number;
    stationPageSize?: number;
}

/**
 * Custom hook for fetching relay and station data from backend.
 * Follows React 19 best practices with race condition prevention.
 */
export function useRelayData(options: UseRelayDataOptions = {}): RelayDataState {
    const { relayPageSize = 50, stationPageSize = 10 } = options;

    const [state, setState] = useState<RelayDataState>({
        relays: [],
        stations: [],
        loading: true,
        error: null
    });

    useEffect(() => {
        let ignore = false;

        const fetchData = async () => {
            try {
                // Fetch relays and stations in parallel
                const [relaysResponse, stationsResponse] = await Promise.all([
                    RelayService.getAll({ page: 0, size: relayPageSize }),
                    LocationService.getAllStations({ page: 0, size: stationPageSize })
                ]);

                // Prevent state updates if component unmounted
                if (!ignore) {
                    // Convert backend relays to legacy Relay model
                    const relays = relaysResponse.data.content.map((backendRelay: BackendRelay) =>
                        Relay.fromBackendRelay(backendRelay)
                    );

                    setState({
                        relays,
                        stations: stationsResponse.data.content,
                        loading: false,
                        error: null
                    });
                }
            } catch (error: unknown) {
                if (!ignore) {
                    const errorMessage = getApiErrorMessage(error, 'Failed to fetch data from backend');

                    if (process.env.NODE_ENV === 'development') {
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
