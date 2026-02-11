import {
    CrossingResponse,
    GetAllCrossingsResponse,
    GetAllStationsResponse,
    GetAllTrackPointsResponse,
    PaginationParams,
    StationResponse,
    TrackPointResponse
} from '../types/relay.types';
import stationsData from './data/stations.json';

class MockLocationService {
    private stations: StationResponse[] = [...stationsData.content];
    private nextId = stationsData.content.length + 1;

    // ========== Stations ==========

    getAllStations(params?: PaginationParams) {
        const page = params?.page ?? 0;
        const size = params?.size ?? 10;
        const start = page * size;
        const paged = this.stations.slice(start, start + size);

        const response: GetAllStationsResponse = {
            content: paged,
            totalElements: this.stations.length,
            totalPages: Math.ceil(this.stations.length / size)
        };

        return Promise.resolve({data: response});
    }

    createStation(name: string) {
        const station: StationResponse = {id: this.nextId++, name};
        this.stations.push(station);
        return Promise.resolve({data: station});
    }

    updateStation(id: number, name: string) {
        const station = this.stations.find(s => s.id === id);
        if (station) {
            station.name = name;
            return Promise.resolve({data: station});
        }
        return Promise.reject(new Error(`Станция с id ${id} не найдена`));
    }

    deleteStation(id: number) {
        const index = this.stations.findIndex(s => s.id === id);
        if (index !== -1) {
            this.stations.splice(index, 1);
            return Promise.resolve({data: null});
        }
        return Promise.reject(new Error(`Станция с id ${id} не найдена`));
    }

    // ========== Track Points ==========

    getAllTrackPoints(params?: PaginationParams) {
        const response: GetAllTrackPointsResponse = {
            content: [],
            totalElements: 0,
            totalPages: 0
        };
        return Promise.resolve({data: response});
    }

    createTrackPoint(name: string) {
        const trackPoint: TrackPointResponse = {id: this.nextId++, name};
        return Promise.resolve({data: trackPoint});
    }

    updateTrackPoint(id: number, name: string) {
        return Promise.resolve({data: {id, name} as TrackPointResponse});
    }

    deleteTrackPoint(id: number) {
        return Promise.resolve({data: null});
    }

    // ========== Crossings ==========

    getAllCrossings(params?: PaginationParams) {
        const response: GetAllCrossingsResponse = {
            content: [],
            totalElements: 0,
            totalPages: 0
        };
        return Promise.resolve({data: response});
    }

    createCrossing(name: string) {
        const crossing: CrossingResponse = {id: this.nextId++, name};
        return Promise.resolve({data: crossing});
    }

    updateCrossing(id: number, name: string) {
        return Promise.resolve({data: {id, name} as CrossingResponse});
    }

    deleteCrossing(id: number) {
        return Promise.resolve({data: null});
    }
}

const mockLocationServiceInstance = new MockLocationService();
export default mockLocationServiceInstance;
