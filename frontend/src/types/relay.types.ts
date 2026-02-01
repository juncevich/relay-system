// Relay Types and DTOs

export interface Relay {
    id: number;
    serialNumber: string;
    relayType?: string;
    createdAt: string;
    lastCheckDate?: string;
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

export interface GetAllRelaysResponse {
    content: Relay[];
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
}

// Location Types

export interface Station {
    id: number;
    name: string;
}

export interface TrackPoint {
    id: number;
    name: string;
}

export interface Crossing {
    id: number;
    name: string;
}

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

export interface GetAllStationsResponse {
    content: StationResponse[];
    totalElements: number;
    totalPages: number;
}

export interface GetAllTrackPointsResponse {
    content: TrackPointResponse[];
    totalElements: number;
    totalPages: number;
}

export interface GetAllCrossingsResponse {
    content: CrossingResponse[];
    totalElements: number;
    totalPages: number;
}

// Storage Types

export interface Warehouse {
    id: number;
    name: string;
    locationId: number;
}

export interface Stand {
    id: number;
    name: string;
    locationId: number;
}

export interface RelayCabinet {
    id: number;
    name: string;
    locationId: number;
}

export interface Shelf {
    id: number;
    number: number;
    capacity: number;
    storageId: number;
}

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

export interface GetAllWarehousesResponse {
    content: WarehouseResponse[];
    totalElements: number;
    totalPages: number;
}

export interface GetAllStandsResponse {
    content: StandResponse[];
    totalElements: number;
    totalPages: number;
}

export interface GetAllRelayCabinetsResponse {
    content: RelayCabinetResponse[];
    totalElements: number;
    totalPages: number;
}

export interface GetAllShelvesResponse {
    content: ShelfResponse[];
    totalElements: number;
    totalPages: number;
}

// Relay Movement Types

export interface RelayMovement {
    id: number;
    relayId: number;
    relaySerialNumber: string;
    fromStorageId: number;
    toStorageId: number;
    movedAt: string;
}

export interface CreateRelayMovementRequest {
    relayId: number;
    fromStorageId: number;
    toStorageId: number;
}

export interface RelayMovementResponse {
    id: number;
    relayId: number;
    relaySerialNumber: string;
    fromStorageId: number;
    toStorageId: number;
    movedAt: string;
}

export interface GetAllRelayMovementsResponse {
    content: RelayMovementResponse[];
    totalElements: number;
    totalPages: number;
}

// Pagination params
export interface PaginationParams {
    page?: number;
    size?: number;
}
