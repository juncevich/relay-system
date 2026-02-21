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

const trackPointsData: TrackPointResponse[] = [
    {id: 7, name: 'Шарташ'},
    {id: 8, name: 'Березит'},
    {id: 9, name: 'Адуй'},
    {id: 10, name: 'Костоусово'}
];

const crossingsData: CrossingResponse[] = [
    {id: 11, name: 'Переезд 39 км'},
    {id: 12, name: 'Переезд 85 км'},
    {id: 13, name: 'Переезд 125 км'}
];

class MockLocationService {
    private stations: StationResponse[] = [...stationsData.content];
    private trackPoints: TrackPointResponse[] = [...trackPointsData];
    private crossings: CrossingResponse[] = [...crossingsData];
    private nextId = 51;

    // ========== Stations ==========

    getAllStations(params?: PaginationParams) {
        const page = params?.page ?? 0;
        const size = params?.size ?? 10;
        const start = page * size;
        const paged = this.stations.slice(start, start + size);

        const response: GetAllStationsResponse = {
            stations: paged,
            totalElements: this.stations.length,
            totalPages: Math.ceil(this.stations.length / size),
            size,
            number: page
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
        const page = params?.page ?? 0;
        const size = params?.size ?? 10;
        const start = page * size;
        const paged = this.trackPoints.slice(start, start + size);

        const response: GetAllTrackPointsResponse = {
            trackPoints: paged,
            totalElements: this.trackPoints.length,
            totalPages: Math.ceil(this.trackPoints.length / size),
            size,
            number: page
        };
        return Promise.resolve({data: response});
    }

    createTrackPoint(name: string) {
        const trackPoint: TrackPointResponse = {id: this.nextId++, name};
        this.trackPoints.push(trackPoint);
        return Promise.resolve({data: trackPoint});
    }

    updateTrackPoint(id: number, name: string) {
        const trackPoint = this.trackPoints.find(tp => tp.id === id);
        if (trackPoint) {
            trackPoint.name = name;
            return Promise.resolve({data: trackPoint});
        }
        return Promise.reject(new Error(`Остановочный пункт с id ${id} не найден`));
    }

    deleteTrackPoint(id: number) {
        const index = this.trackPoints.findIndex(tp => tp.id === id);
        if (index !== -1) {
            this.trackPoints.splice(index, 1);
            return Promise.resolve({data: null});
        }
        return Promise.reject(new Error(`Остановочный пункт с id ${id} не найден`));
    }

    // ========== Crossings ==========

    getAllCrossings(params?: PaginationParams) {
        const page = params?.page ?? 0;
        const size = params?.size ?? 10;
        const start = page * size;
        const paged = this.crossings.slice(start, start + size);

        const response: GetAllCrossingsResponse = {
            crossings: paged,
            totalElements: this.crossings.length,
            totalPages: Math.ceil(this.crossings.length / size),
            size,
            number: page
        };
        return Promise.resolve({data: response});
    }

    createCrossing(name: string) {
        const crossing: CrossingResponse = {id: this.nextId++, name};
        this.crossings.push(crossing);
        return Promise.resolve({data: crossing});
    }

    updateCrossing(id: number, name: string) {
        const crossing = this.crossings.find(c => c.id === id);
        if (crossing) {
            crossing.name = name;
            return Promise.resolve({data: crossing});
        }
        return Promise.reject(new Error(`Переезд с id ${id} не найден`));
    }

    deleteCrossing(id: number) {
        const index = this.crossings.findIndex(c => c.id === id);
        if (index !== -1) {
            this.crossings.splice(index, 1);
            return Promise.resolve({data: null});
        }
        return Promise.reject(new Error(`Переезд с id ${id} не найден`));
    }
}

const mockLocationServiceInstance = new MockLocationService();
export default mockLocationServiceInstance;
