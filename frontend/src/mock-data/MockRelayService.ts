import {GetAllRelaysResponse, PaginationParams} from '../types/relay.types';
import relaysData from './data/relays.json';

class MockRelayService {
    getAll(params?: PaginationParams) {
        const page = params?.page ?? 0;
        const size = params?.size ?? 10;
        const allRelays = relaysData.content;
        const start = page * size;
        const paged = allRelays.slice(start, start + size);

        const response: GetAllRelaysResponse = {
            content: paged,
            totalElements: allRelays.length,
            totalPages: Math.ceil(allRelays.length / size),
            size,
            number: page
        };

        return Promise.resolve({data: response});
    }
}

const mockRelayServiceInstance = new MockRelayService();
export default mockRelayServiceInstance;
