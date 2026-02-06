import {GetAllStationsResponse, PaginationParams} from '../types/relay.types';
import stationsData from './data/stations.json';

class MockLocationService {
    getAllStations(params?: PaginationParams) {
        const page = params?.page ?? 0;
        const size = params?.size ?? 10;
        const allStations = stationsData.content;
        const start = page * size;
        const paged = allStations.slice(start, start + size);

        const response: GetAllStationsResponse = {
            content: paged,
            totalElements: allStations.length,
            totalPages: Math.ceil(allStations.length / size)
        };

        return Promise.resolve({data: response});
    }
}

const mockLocationServiceInstance = new MockLocationService();
export default mockLocationServiceInstance;
