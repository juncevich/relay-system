import {
    CreateRelayRequest,
    CreateRelayResponse,
    GetAllRelaysResponse,
    PaginationParams,
    Relay,
    UpdateRelayRequest
} from '../types/relay.types';
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

    getById(id: number) {
        const relay = relaysData.content.find(r => r.id === id);
        if (relay) {
            return Promise.resolve({data: relay as Relay});
        }
        return Promise.reject(new Error(`Relay with id ${id} not found`));
    }

    create(data: CreateRelayRequest) {
        const response: CreateRelayResponse = {
            serialNumber: data.serialNumber,
            dateOfManufacture: data.dateOfManufacture,
        };
        return Promise.resolve({data: response});
    }

    update(id: number, data: UpdateRelayRequest) {
        const relay = relaysData.content.find(r => r.id === id);
        if (relay) {
            return Promise.resolve({data: {...relay, ...data} as Relay});
        }
        return Promise.reject(new Error(`Relay with id ${id} not found`));
    }

    delete(id: number) {
        return Promise.resolve({data: null});
    }
}

const mockRelayServiceInstance = new MockRelayService();
export default mockRelayServiceInstance;
