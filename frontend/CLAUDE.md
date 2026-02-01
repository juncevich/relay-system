# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a React + TypeScript frontend application for a relay management system. The app uses Create React App as its foundation and Ant Design (antd) as the UI component library. The application displays relay cards in a tabbed interface with location-based navigation.

**Current Versions (as of February 2026):**
- React: 19.2.4
- Ant Design: 6.2.2
- TypeScript: Strict mode with React 19 JSX transform
- Axios: 1.13.4

## Development Commands

### Running the Application

```bash
npm start
# or
yarn start
```

Starts the development server on http://localhost:3000

### Building

```bash
npm run build
# or
yarn build
```

Creates an optimized production build in the `build/` folder.

### Testing

```bash
npm test
# or
yarn test
```

Runs tests in interactive watch mode using react-scripts test runner.

### Docker Build

```bash
docker build -t alexunc/rs-frontend:0.0.1 .
```

### Running with Docker

```bash
docker run -d --name rs_frontend -p3000:3000 alexunc/rs-frontend:0.0.1
```

### Kubernetes Development with Tilt

The project uses Tilt for Kubernetes-based development:

```bash
tilt up
```

This will:
- Deploy to the `relay-system-dev` namespace
- Build and run the Docker image `alexunc/rs-frontend`
- Enable live updates (syncs changes to `/app` and reinstalls on package.json/yarn.lock changes)
- Forward port 3000 for local access

## Architecture

### Project Structure

```
src/
├── api/              # API client and HTTP configuration
│   ├── http-common.ts   # Axios instance configured for backend API
│   └── RelayService.ts  # API service methods (currently unused)
├── components/       # React components
│   ├── mainTab/         # Main tabbed layout component
│   └── relay/           # Relay card component
├── models/           # TypeScript data models
│   └── Relay.ts         # Relay model class
├── pages/            # Page-level components
│   └── MainPage.tsx     # Main application page
├── App.tsx           # Root application component
└── index.tsx         # Application entry point
```

### Component Hierarchy

- **App.tsx**: Root component that renders MainPage
- **MainPage.tsx**: Wrapper component that renders MainTab
- **MainTab.tsx**: Primary layout component with:
  - Header with navigation menu
  - Breadcrumb navigation
  - Sidebar menu (location-based: Свердловский участок with submenu items)
  - Tabbed content area (3 tabs)
  - Grid layout of RelayCard components (8 cards per row)
- **RelayCard.tsx**: Individual relay display card showing:
  - Relay image
  - Title
  - Status icon
  - Checking date
  - Action buttons (Settings, Edit, Ellipsis)

### API Configuration

The API client is configured in `src/api/http-common.ts`:
- Base URL: `http://localhost:8080/api`
- Uses Axios for HTTP requests
- JSON content type headers

**Note**: The RelayService is currently stubbed out and not in use. The application uses hardcoded relay data.

### Data Model

**Relay** (`src/models/Relay.ts`):
- `imgUrl: string` - URL to relay image
- `title: string` - Relay model name/identifier
- `checkingDate: string` - Date of last check/inspection

### UI Framework

The application uses Ant Design v6.2.2 with the following key components:
- Layout (Header, Content, Footer, Sider)
- Menu and Breadcrumb for navigation (using `items` prop pattern)
- Tabs for content organization (using `items` prop pattern)
- Card for relay display
- Grid system (Row/Col) for responsive layout

**Important Ant Design 6 Patterns:**
- Menu components use the `items` prop instead of `Menu.Item` children
- Tabs use the `items` prop instead of `TabPane` children
- Breadcrumb uses the `items` prop instead of `Breadcrumb.Item` children
- All menu items are objects with `key`, `label`, and optional `children` properties

## TypeScript Configuration

- Target: ES5
- Module: ESNext with Node resolution
- Strict mode enabled
- JSX: react-jsx (React 19 automatic JSX transform)
- All source files in `src/` directory

## React 19 Patterns

**Key changes from React 18:**
- Components must return `null` instead of `undefined`
- `ref` is now a regular prop (no need for `forwardRef` in most cases)
- Improved TypeScript support with updated type definitions
- Use the new JSX transform (`react-jsx`) instead of the classic transform

## Backend Integration

The frontend is integrated with the relay-store-service backend at `http://localhost:8082`.

**Backend Services:**
- `RelayService` (`src/api/RelayService.ts`) - Full CRUD operations for relays
- `LocationService` (`src/api/LocationService.ts`) - Manage stations, track points, and crossings

**Type Definitions:**
- All backend DTOs are defined in `src/types/relay.types.ts`
- Includes Relay, Location, Storage, and RelayMovement types
- Pagination support with `PaginationParams` interface

**Data Fetching Pattern:**
```typescript
// Example from MainTab.tsx
useEffect(() => {
    const fetchData = async () => {
        const [relaysResponse, stationsResponse] = await Promise.all([
            RelayService.getAll({ page: 0, size: 50 }),
            LocationService.getAllStations({ page: 0, size: 10 })
        ]);
        // Convert and update state
    };
    fetchData();
}, []);
```

**Important Notes:**
- MainTab component fetches real data from backend on mount
- Includes loading states (Spin component) and error handling (Alert component)
- Legacy Relay model includes `fromBackendRelay()` helper for data conversion
- Sidebar menu is dynamically populated with stations from backend

See `BACKEND_INTEGRATION.md` for complete integration documentation.

## Development Notes

- The codebase contains commented-out class component versions in both MainTab.tsx and RelayCard.tsx - these show the original class-based implementation before migration to functional components
- Currently, relay data is hardcoded in MainTab component - future work will likely connect to the backend API
- The application is designed for deployment in Kubernetes with Tilt-based local development workflow
