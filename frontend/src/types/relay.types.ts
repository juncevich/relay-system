// Relay Types and DTOs

export interface PaginatedResponse {
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
}

export interface Relay {
    id: number;
    serialNumber: string;
    relayType?: string;
    createdAt: string;
    verificationDate?: string;
    placeNumber?: number;
    storageId?: number;
    shelfId?: number;
}

export interface CreateRelayRequest {
    serialNumber: string;
    dateOfManufacture: string;
    storageId: number;
}

export interface UpdateRelayRequest {
    serialNumber?: string;
    dateOfManufacture?: string;
    verificationDate?: string;
    storageId?: number;
}

export interface CreateRelayResponse {
    serialNumber: string;
    dateOfManufacture: string;
    verificationDate?: string;
}

export interface GetAllRelaysResponse extends PaginatedResponse {
    relays: Relay[];
}

// Location Types

export interface StationResponse {
    id: number;
    name: string;
}

export interface TrackPointResponse {
    id: number;
    name: string;
}

export interface CrossingResponse {
    id: number;
    name: string;
}

export type Station = StationResponse;
export type TrackPoint = TrackPointResponse;
export type Crossing = CrossingResponse;

export interface GetAllStationsResponse extends PaginatedResponse {
    stations: StationResponse[];
}

export interface GetAllTrackPointsResponse extends PaginatedResponse {
    trackPoints: TrackPointResponse[];
}

export interface GetAllCrossingsResponse extends PaginatedResponse {
    crossings: CrossingResponse[];
}

// Storage Types

export interface WarehouseResponse {
    id: number;
    name: string;
    locationId: number;
}

export interface StandResponse {
    id: number;
    name: string;
    locationId: number;
}

export interface RelayCabinetResponse {
    id: number;
    name: string;
    locationId: number;
}

export interface ShelfResponse {
    id: number;
    number: number;
    capacity: number;
    storageId: number;
}

export type Warehouse = WarehouseResponse;
export type Stand = StandResponse;
export type RelayCabinet = RelayCabinetResponse;
export type Shelf = ShelfResponse;

export interface GetAllWarehousesResponse extends PaginatedResponse {
    warehouses: WarehouseResponse[];
}

export interface GetAllStandsResponse extends PaginatedResponse {
    stands: StandResponse[];
}

export interface GetAllRelayCabinetsResponse extends PaginatedResponse {
    relayCabinets: RelayCabinetResponse[];
}

export interface GetAllShelvesResponse extends PaginatedResponse {
    shelves: ShelfResponse[];
}

// Relay Movement Types

export interface RelayMovementResponse {
    id: number;
    relayId: number;
    relaySerialNumber: string;
    fromStorageId: number;
    toStorageId: number;
    movedAt: string;
}

export type RelayMovement = RelayMovementResponse;

export interface CreateRelayMovementRequest {
    relayId: number;
    fromStorageId: number;
    toStorageId: number;
}

export interface GetAllRelayMovementsResponse extends PaginatedResponse {
    relayMovements: RelayMovementResponse[];
}

// Pagination params
export interface PaginationParams {
    page?: number;
    size?: number;
}

// API Error type for type-safe error handling
export interface ApiError {
    response?: {
        data?: {
            message?: string;
            error?: string;
            status?: number;
        };
        status?: number;
    };
    message?: string;
}

// Type guard for API errors
export function isApiError(error: unknown): error is ApiError {
    return (
        typeof error === 'object' &&
        error !== null &&
        ('response' in error || 'message' in error)
    );
}

// Helper to extract error message from API error
export function getApiErrorMessage(error: unknown, defaultMessage = 'An error occurred'): string {
    if (isApiError(error)) {
        return error.response?.data?.message ||
               error.response?.data?.error ||
               error.message ||
               defaultMessage;
    }
    if (error instanceof Error) {
        return error.message;
    }
    return defaultMessage;
}
