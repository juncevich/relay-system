import {
    CreateRelayRequest,
    CreateRelayResponse,
    GetAllRelaysResponse,
    PaginationParams,
    Relay,
    UpdateRelayRequest
} from '../types/relay.types';
import relaysData from './data/relays.json';

// Map mock data lastCheckDate to verificationDate to match backend response
const allRelays: Relay[] = relaysData.content.map((r: Record<string, unknown>) => ({
    id: r.id as number,
    serialNumber: r.serialNumber as string,
    relayType: r.relayType as string | undefined,
    createdAt: r.createdAt as string,
    verificationDate: r.lastCheckDate as string | undefined,
    placeNumber: r.placeNumber as number | undefined,
    storageId: r.storageId as number | undefined,
    shelfId: r.shelfId as number | undefined,
}));

class MockRelayService {
    getAll(params?: PaginationParams) {
        const page = params?.page ?? 0;
        const size = params?.size ?? 10;
        const start = page * size;
        const paged = allRelays.slice(start, start + size);

        const response: GetAllRelaysResponse = {
            relays: paged,
            totalElements: allRelays.length,
            totalPages: Math.ceil(allRelays.length / size),
            size,
            number: page
        };

        return Promise.resolve({data: response});
    }

    getById(id: number) {
        const relay = allRelays.find(r => r.id === id);
        if (relay) {
            return Promise.resolve({data: relay});
        }
        return Promise.reject(new Error(`Relay with id ${id} not found`));
    }

    getBySerialNumber(serialNumber: string) {
        const relay = allRelays.find(r => r.serialNumber === serialNumber);
        if (relay) {
            return Promise.resolve({data: relay});
        }
        return Promise.reject(new Error(`Relay with serial number ${serialNumber} not found`));
    }

    create(data: CreateRelayRequest) {
        const response: CreateRelayResponse = {
            serialNumber: data.serialNumber,
            dateOfManufacture: data.dateOfManufacture,
        };
        return Promise.resolve({data: response});
    }

    update(id: number, data: UpdateRelayRequest) {
        const relay = allRelays.find(r => r.id === id);
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
