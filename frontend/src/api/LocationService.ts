import {relayStoreHttp} from './http-common';
import {
    CrossingResponse,
    GetAllCrossingsResponse,
    GetAllStationsResponse,
    GetAllTrackPointsResponse,
    PaginationParams,
    StationResponse,
    TrackPointResponse
} from '../types/relay.types';

class LocationService {
    // ========== Stations ==========

    /**
     * Get all stations with pagination
     * @param params - Pagination parameters (page, size)
     * @returns Promise with paginated station data
     */
    getAllStations(params?: PaginationParams) {
        return relayStoreHttp.get<GetAllStationsResponse>('/stations', {
            params: {
                page: params?.page ?? 0,
                size: params?.size ?? 10
            }
        });
    }

    /**
     * Get station by ID
     * @param id - Station ID
     * @returns Promise with station data
     */
    getStationById(id: number) {
        return relayStoreHttp.get<StationResponse>(`/stations/${id}`);
    }

    /**
     * Create a new station
     * @param name - Station name
     * @returns Promise with created station
     */
    createStation(name: string) {
        return relayStoreHttp.post<StationResponse>('/stations', {name});
    }

    /**
     * Update an existing station
     * @param id - Station ID
     * @param name - New station name
     * @returns Promise with updated station
     */
    updateStation(id: number, name: string) {
        return relayStoreHttp.put<StationResponse>(`/stations/${id}`, {name});
    }

    /**
     * Delete a station
     * @param id - Station ID
     * @returns Promise
     */
    deleteStation(id: number) {
        return relayStoreHttp.delete(`/stations/${id}`);
    }

    // ========== Track Points ==========

    /**
     * Get all track points with pagination
     * @param params - Pagination parameters (page, size)
     * @returns Promise with paginated track point data
     */
    getAllTrackPoints(params?: PaginationParams) {
        return relayStoreHttp.get<GetAllTrackPointsResponse>('/track-points', {
            params: {
                page: params?.page ?? 0,
                size: params?.size ?? 10
            }
        });
    }

    /**
     * Get track point by ID
     * @param id - Track point ID
     * @returns Promise with track point data
     */
    getTrackPointById(id: number) {
        return relayStoreHttp.get<TrackPointResponse>(`/track-points/${id}`);
    }

    /**
     * Create a new track point
     * @param name - Track point name
     * @returns Promise with created track point
     */
    createTrackPoint(name: string) {
        return relayStoreHttp.post<TrackPointResponse>('/track-points', {name});
    }

    /**
     * Update an existing track point
     * @param id - Track point ID
     * @param name - New track point name
     * @returns Promise with updated track point
     */
    updateTrackPoint(id: number, name: string) {
        return relayStoreHttp.put<TrackPointResponse>(`/track-points/${id}`, {name});
    }

    /**
     * Delete a track point
     * @param id - Track point ID
     * @returns Promise
     */
    deleteTrackPoint(id: number) {
        return relayStoreHttp.delete(`/track-points/${id}`);
    }

    // ========== Crossings ==========

    /**
     * Get all crossings with pagination
     * @param params - Pagination parameters (page, size)
     * @returns Promise with paginated crossing data
     */
    getAllCrossings(params?: PaginationParams) {
        return relayStoreHttp.get<GetAllCrossingsResponse>('/crossings', {
            params: {
                page: params?.page ?? 0,
                size: params?.size ?? 10
            }
        });
    }

    /**
     * Get crossing by ID
     * @param id - Crossing ID
     * @returns Promise with crossing data
     */
    getCrossingById(id: number) {
        return relayStoreHttp.get<CrossingResponse>(`/crossings/${id}`);
    }

    /**
     * Create a new crossing
     * @param name - Crossing name
     * @returns Promise with created crossing
     */
    createCrossing(name: string) {
        return relayStoreHttp.post<CrossingResponse>('/crossings', {name});
    }

    /**
     * Update an existing crossing
     * @param id - Crossing ID
     * @param name - New crossing name
     * @returns Promise with updated crossing
     */
    updateCrossing(id: number, name: string) {
        return relayStoreHttp.put<CrossingResponse>(`/crossings/${id}`, {name});
    }

    /**
     * Delete a crossing
     * @param id - Crossing ID
     * @returns Promise
     */
    deleteCrossing(id: number) {
        return relayStoreHttp.delete(`/crossings/${id}`);
    }
}

export {LocationService};

const locationServiceInstance = new LocationService();
export default locationServiceInstance;
