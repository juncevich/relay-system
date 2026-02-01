# Backend Integration Guide

This document describes how the frontend integrates with the relay-store-service backend.

## Backend Configuration

**Backend Server:**
- Base URL: `http://localhost:8082`
- API Documentation: `http://localhost:8082/swagger-ui.html`
- Database: PostgreSQL at `localhost:5432/relay-service`

## API Services

### 1. RelayService (`src/api/RelayService.ts`)

Handles all relay-related API operations.

**Available Methods:**

```typescript
// Get all relays with pagination
RelayService.getAll({ page: 0, size: 10 })

// Get relay by ID
RelayService.getById(id)

// Find relay by serial number
RelayService.getBySerialNumber(serialNumber)

// Find relays by creation date
RelayService.getByCreationDate(date, { page: 0, size: 10 })

// Find relays by last check date
RelayService.getByLastCheckDate(date, { page: 0, size: 10 })

// Create new relay
RelayService.create({ serialNumber, dateOfManufacture, storageId })

// Update relay
RelayService.update(id, { serialNumber, dateOfManufacture, verificationDate, storageId })

// Delete relay
RelayService.delete(id)
```

**Endpoints:**
- `GET /relays` - Get all relays (paginated)
- `GET /relays/{id}` - Get relay by ID
- `GET /relays/serial-number/{serialNumber}` - Find by serial number
- `GET /relays/by-creation-date?date={date}` - Find by creation date
- `GET /relays/by-last-check-date?date={date}` - Find by last check date
- `POST /relays` - Create relay
- `PUT /relays/{id}` - Update relay
- `DELETE /relays/{id}` - Delete relay

### 2. LocationService (`src/api/LocationService.ts`)

Handles location-related API operations for stations, track points, and crossings.

**Station Methods:**

```typescript
// Get all stations
LocationService.getAllStations({ page: 0, size: 10 })

// Get station by ID
LocationService.getStationById(id)

// Create station
LocationService.createStation(name)

// Update station
LocationService.updateStation(id, name)

// Delete station
LocationService.deleteStation(id)
```

**Track Point Methods:**

```typescript
LocationService.getAllTrackPoints({ page: 0, size: 10 })
LocationService.getTrackPointById(id)
LocationService.createTrackPoint(name)
LocationService.updateTrackPoint(id, name)
LocationService.deleteTrackPoint(id)
```

**Crossing Methods:**

```typescript
LocationService.getAllCrossings({ page: 0, size: 10 })
LocationService.getCrossingById(id)
LocationService.createCrossing(name)
LocationService.updateCrossing(id, name)
LocationService.deleteCrossing(id)
```

**Endpoints:**
- `GET /stations`, `POST /stations`, `PUT /stations/{id}`, `DELETE /stations/{id}`
- `GET /track-points`, `POST /track-points`, `PUT /track-points/{id}`, `DELETE /track-points/{id}`
- `GET /crossings`, `POST /crossings`, `PUT /crossings/{id}`, `DELETE /crossings/{id}`

## TypeScript Types

All backend DTOs are defined in `src/types/relay.types.ts`:

### Relay Types

```typescript
interface Relay {
    id: number;
    serialNumber: string;
    relayType?: string;
    createdAt: string;
    lastCheckDate?: string;
    placeNumber?: number;
    storageId?: number;
    shelfId?: number;
}

interface CreateRelayRequest {
    serialNumber: string;
    dateOfManufacture: string;
    storageId: number;
}

interface UpdateRelayRequest {
    serialNumber?: string;
    dateOfManufacture?: string;
    verificationDate?: string;
    storageId?: number;
}

interface GetAllRelaysResponse {
    content: Relay[];
    totalElements: number;
    totalPages: number;
    size: number;
    number: number;
}
```

### Location Types

```typescript
interface Station {
    id: number;
    name: string;
}

interface TrackPoint {
    id: number;
    name: string;
}

interface Crossing {
    id: number;
    name: string;
}
```

### Storage Types

```typescript
interface Warehouse {
    id: number;
    name: string;
    locationId: number;
}

interface Stand {
    id: number;
    name: string;
    locationId: number;
}

interface RelayCabinet {
    id: number;
    name: string;
    locationId: number;
}

interface Shelf {
    id: number;
    number: number;
    capacity: number;
    storageId: number;
}
```

## Pagination

All list endpoints support pagination with these parameters:

```typescript
interface PaginationParams {
    page?: number;  // Default: 0
    size?: number;  // Default: 10
}
```

## Component Integration

### MainTab Component

The `MainTab` component (`src/components/mainTab/MainTab.tsx`) demonstrates how to fetch and display backend data:

**Key Features:**

1. **Data Fetching on Mount:**
```typescript
useEffect(() => {
    const fetchData = async () => {
        try {
            const [relaysResponse, stationsResponse] = await Promise.all([
                RelayService.getAll({ page: 0, size: 50 }),
                LocationService.getAllStations({ page: 0, size: 10 })
            ]);

            const relays = relaysResponse.data.content.map(backendRelay =>
                Relay.fromBackendRelay(backendRelay)
            );

            setState({ relays, stations: stationsResponse.data.content, loading: false });
        } catch (error) {
            setState({ error: error.message, loading: false });
        }
    };

    fetchData();
}, []);
```

2. **Loading State:**
```typescript
{state.loading ? (
    <Spin size="large" tip="Загрузка реле..." />
) : (
    // Display content
)}
```

3. **Error Handling:**
```typescript
{state.error && (
    <Alert
        message="Ошибка загрузки данных"
        description={state.error}
        type="error"
        showIcon
    />
)}
```

4. **Dynamic Menu from Backend:**
```typescript
<Menu
    items={[
        {
            key: 'stations',
            label: 'Станции',
            children: state.stations.map(station => ({
                key: `station-${station.id}`,
                label: station.name
            }))
        },
    ]}
/>
```

## Legacy Model Compatibility

The `Relay` model in `src/models/Relay.ts` maintains backward compatibility with existing UI components:

```typescript
class Relay {
    imgUrl: string;
    title: string;
    checkingDate: string;

    static fromBackendRelay(backendRelay: {
        id: number;
        serialNumber: string;
        lastCheckDate?: string;
        relayType?: string;
    }): Relay {
        return new Relay(
            'http://www.status-scb.ru/upload/iblock/458/458aa8a30c03af897511a2d8c00cdc74.png',
            backendRelay.serialNumber,
            backendRelay.lastCheckDate
                ? new Date(backendRelay.lastCheckDate).toLocaleDateString('ru-RU')
                : 'Не проверено'
        );
    }
}
```

This allows converting backend `Relay` entities to the format expected by `RelayCard` components.

## Error Handling

The integration includes comprehensive error handling:

1. **Network Errors:** Caught and displayed to users
2. **Backend Errors:** Response data extracted and shown
3. **Loading States:** Spinners displayed during data fetching
4. **Empty States:** Informative messages when no data is available

## Testing the Integration

### 1. Start the Backend

```bash
cd ../relay-store-service
./gradlew bootRun
```

The backend should be running on `http://localhost:8082`

### 2. Verify Backend is Running

Open `http://localhost:8082/swagger-ui.html` in your browser to see API documentation.

### 3. Start the Frontend

```bash
npm start
```

The frontend will connect to the backend and fetch data automatically.

### 4. Expected Behavior

- **With Backend Running:**
  - Relay cards display actual data from the database
  - Sidebar menu shows real stations from the backend
  - Tab labels show relay counts

- **Without Backend Running:**
  - Error message displayed: "Failed to fetch data from backend"
  - Loading spinner shown briefly
  - Error alert with details

## Future Enhancements

Potential improvements to the backend integration:

1. **Create Additional Services:**
   - `StorageService.ts` for warehouses, stands, relay cabinets
   - `ShelfService.ts` for shelf management
   - `RelayMovementService.ts` for tracking relay movements

2. **Add Search and Filtering:**
   - Filter relays by serial number
   - Search by date ranges
   - Filter by storage location

3. **Implement CRUD Operations:**
   - Add forms to create/edit relays
   - Add modal dialogs for confirmations
   - Implement optimistic updates

4. **Add Real-time Updates:**
   - WebSocket integration for live updates
   - Auto-refresh on data changes
   - Notifications for relay movements

5. **Improve Error Handling:**
   - Retry failed requests
   - Offline mode support
   - Better error messages

6. **Add Authentication:**
   - JWT token management
   - Login/logout functionality
   - Protected routes

## Troubleshooting

### Backend Not Responding

**Problem:** Error message "Failed to fetch data from backend"

**Solutions:**
1. Verify backend is running: `curl http://localhost:8082/actuator/health`
2. Check backend logs for errors
3. Verify PostgreSQL database is running
4. Check CORS configuration in backend

### CORS Errors

**Problem:** Browser console shows CORS policy errors

**Solution:** Ensure the backend `CorsConfig` allows requests from `http://localhost:3000`

### Wrong Port

**Problem:** Backend runs on different port

**Solution:** Update `src/api/http-common.ts` baseURL to match your backend port

### Type Errors

**Problem:** TypeScript errors about incompatible types

**Solution:** Ensure `src/types/relay.types.ts` matches the backend DTOs exactly
