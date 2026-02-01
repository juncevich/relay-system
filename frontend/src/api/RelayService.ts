import http from './http-common';
import {
    Relay,
    GetAllRelaysResponse,
    CreateRelayRequest,
    CreateRelayResponse,
    UpdateRelayRequest,
    PaginationParams
} from '../types/relay.types';

class RelayService {
    /**
     * Get all relays with pagination
     * @param params - Pagination parameters (page, size)
     * @returns Promise with paginated relay data
     */
    getAll(params?: PaginationParams) {
        return http.get<GetAllRelaysResponse>('/relays', {
            params: {
                page: params?.page ?? 0,
                size: params?.size ?? 10
            }
        });
    }

    /**
     * Get relay by ID
     * @param id - Relay ID
     * @returns Promise with relay data
     */
    getById(id: number) {
        return http.get<Relay>(`/relays/${id}`);
    }

    /**
     * Find relay by serial number
     * @param serialNumber - Relay serial number
     * @returns Promise with relay data
     */
    getBySerialNumber(serialNumber: string) {
        return http.get<Relay>(`/relays/serial-number/${serialNumber}`);
    }

    /**
     * Find relays by creation date
     * @param date - Creation date in ISO format
     * @param params - Pagination parameters
     * @returns Promise with paginated relay data
     */
    getByCreationDate(date: string, params?: PaginationParams) {
        return http.get<GetAllRelaysResponse>('/relays/by-creation-date', {
            params: {
                date,
                page: params?.page ?? 0,
                size: params?.size ?? 10
            }
        });
    }

    /**
     * Find relays by last check date
     * @param date - Last check date in ISO format
     * @param params - Pagination parameters
     * @returns Promise with paginated relay data
     */
    getByLastCheckDate(date: string, params?: PaginationParams) {
        return http.get<GetAllRelaysResponse>('/relays/by-last-check-date', {
            params: {
                date,
                page: params?.page ?? 0,
                size: params?.size ?? 10
            }
        });
    }

    /**
     * Create a new relay
     * @param data - Relay creation data
     * @returns Promise with created relay response
     */
    create(data: CreateRelayRequest) {
        return http.post<CreateRelayResponse>('/relays', data);
    }

    /**
     * Update an existing relay
     * @param id - Relay ID
     * @param data - Relay update data
     * @returns Promise with updated relay
     */
    update(id: number, data: UpdateRelayRequest) {
        return http.put<Relay>(`/relays/${id}`, data);
    }

    /**
     * Delete a relay
     * @param id - Relay ID
     * @returns Promise
     */
    delete(id: number) {
        return http.delete(`/relays/${id}`);
    }
}

const relayServiceInstance = new RelayService();
export default relayServiceInstance;
