import {GetAllStationsResponse, PaginationParams, StationResponse} from '../types/relay.types';
import stationsData from './data/stations.json';

class MockLocationService {
    private stations: StationResponse[] = [...stationsData.content];
    private nextId = stationsData.content.length + 1;

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
}

const mockLocationServiceInstance = new MockLocationService();
export default mockLocationServiceInstance;
