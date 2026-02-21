import {relayStoreHttp} from './http-common';
import {
    GetAllRelayCabinetsResponse,
    GetAllStandsResponse,
    GetAllWarehousesResponse,
    PaginationParams,
    RelayCabinetResponse,
    StandResponse,
    WarehouseResponse
} from '../types/relay.types';

export interface StorageInfo {
    id: number;
    name: string;
    locationId: number;
}

class StorageService {
    getAllWarehouses(params?: PaginationParams) {
        return relayStoreHttp.get<GetAllWarehousesResponse>('/warehouses', {
            params: {
                page: params?.page ?? 0,
                size: params?.size ?? 100
            }
        });
    }

    getAllStands(params?: PaginationParams) {
        return relayStoreHttp.get<GetAllStandsResponse>('/stands', {
            params: {
                page: params?.page ?? 0,
                size: params?.size ?? 100
            }
        });
    }

    getAllRelayCabinets(params?: PaginationParams) {
        return relayStoreHttp.get<GetAllRelayCabinetsResponse>('/relay-cabinets', {
            params: {
                page: params?.page ?? 0,
                size: params?.size ?? 100
            }
        });
    }

    async getAllStorages(): Promise<StorageInfo[]> {
        const [warehouses, stands, cabinets] = await Promise.all([
            this.getAllWarehouses(),
            this.getAllStands(),
            this.getAllRelayCabinets()
        ]);

        return [
            ...warehouses.data.warehouses.map((w: WarehouseResponse) => ({
                id: w.id,
                name: w.name,
                locationId: w.locationId
            })),
            ...stands.data.stands.map((s: StandResponse) => ({id: s.id, name: s.name, locationId: s.locationId})),
            ...cabinets.data.relayCabinets.map((c: RelayCabinetResponse) => ({
                id: c.id,
                name: c.name,
                locationId: c.locationId
            }))
        ];
    }
}

export {StorageService};

const storageServiceInstance = new StorageService();
export default storageServiceInstance;
