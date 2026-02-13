# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is a React + TypeScript frontend application for a relay management system. The app uses **Vite** as the build tool
and **Ant Design (antd)** as the UI component library. The application displays relay cards in a tabbed interface with
location-based navigation.

**Current Versions (as of February 2026):**
- React: 19.2.4
- Ant Design: 6.3.0
- TypeScript: Strict mode with React 19 JSX transform
- Vite: 7.x
- Vitest: 4.x (test runner)
- Axios: 1.13.5
- react-router: 7.x (routing)

## Development Commands

### Running the Application

```bash
npm run dev
# or
npm start
```

Starts the Vite dev server on http://localhost:3000

### Running with Mock Data

```bash
npm run start:mock
```

Uses `VITE_USE_MOCK_DATA=true` to load local JSON mock data instead of the backend API.

### Building

```bash
npm run build
```

Runs `tsc && vite build`, producing an optimized production build in the `build/` folder.

### Preview Production Build

```bash
npm run preview
```

Serves the production build locally using Vite's preview server.

### Testing

```bash
npm test          # Single run (vitest run)
npm run test:watch  # Watch mode (vitest)
```

Tests use **Vitest** with jsdom environment. Test files use `vi.mock`, `vi.fn`, etc. (not Jest globals).

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
- Enable live updates (syncs changes to `/app` and reinstalls on package.json changes)
- Forward port 3000 for local access

## Architecture

### Project Structure

```
src/
├── api/              # API client and HTTP configuration
│   ├── api-urls.ts      # Base URL constants
│   ├── http-common.ts   # Axios instance for relay-store backend
│   ├── RelayService.ts  # Relay CRUD API service
│   └── LocationService.ts # Location API service (stations, track points, crossings)
├── components/       # React components
│   ├── errorBoundary/   # Error boundary component
│   ├── layout/          # AppLayout with routing and top-level navigation
│   ├── mainTab/         # Main tabbed layout component
│   └── relay/           # Relay card component
├── hooks/            # Custom React hooks
│   └── useRelayData.ts  # Data fetching hook (supports mock/real data)
├── mock-data/        # Mock service implementations
├── models/           # TypeScript data models
│   └── Relay.ts         # Relay model class
├── pages/            # Page-level components
│   ├── HomePage.tsx     # Home/landing page
│   ├── MainPage.tsx     # Main relay management page
│   └── StationsPage.tsx # Stations management page
├── test-utils/       # Test utilities and mock data
├── types/            # TypeScript type definitions
│   └── relay.types.ts   # Backend DTO types
├── App.tsx           # Root component with ConfigProvider + AntApp wrapper
├── index.tsx         # Application entry point
└── vite-env.d.ts     # Vite environment type declarations
```

### Component Hierarchy

- **App.tsx**: Root component with Ant Design `ConfigProvider`, `App` wrapper, and `BrowserRouter`
- **AppLayout.tsx**: Top-level layout with header navigation and `<Outlet/>` for routed pages
- **HomePage.tsx**: Home/landing page
- **StationsPage.tsx**: Stations management page
- **MainPage.tsx**: Wrapper component that renders MainTab
- **MainTab.tsx**: Primary layout component with:
  - Header with navigation menu
  - Breadcrumb navigation
  - Sidebar menu (dynamically populated with stations from backend)
  - Tabbed content area (3 tabs)
  - Grid layout of RelayCard components (8 cards per row)
  - ErrorBoundary wrapper
- **RelayCard.tsx**: Individual relay display card showing:
  - Relay image
  - Title
  - Status icon
  - Checking date
  - Action buttons (Settings, Edit, Ellipsis)

### API Configuration

The API client is configured in `src/api/http-common.ts`:

- Base URL: `http://localhost:8082` (relay-store-service), configurable via `VITE_API_BASE_URL`
- Uses Axios for HTTP requests
- JSON content type headers

### Environment Variables

Environment variables use the `VITE_` prefix (Vite convention):

- `VITE_USE_MOCK_DATA` — Set to `"true"` to use mock data instead of backend API
- `VITE_API_BASE_URL` — (optional) Override API base URL

Access via `import.meta.env.VITE_*` (not `process.env`).

### Data Model

**Relay** (`src/models/Relay.ts`):

- `id: number` - Relay ID
- `imgUrl: string` - URL to relay image
- `title: string` - Relay model name/identifier
- `checkingDate: string` - Date of last check/inspection

### UI Framework

The application uses Ant Design v6.3.0 with the following key components:
- Layout (Header, Content, Footer, Sider)
- Menu and Breadcrumb for navigation (using `items` prop pattern)
- Tabs for content organization (using `items` prop pattern)
- Card for relay display
- Grid system (Row/Col) for responsive layout
- ConfigProvider for theme configuration

**Important Ant Design 6 Patterns:**
- Menu components use the `items` prop instead of `Menu.Item` children
- Tabs use the `items` prop instead of `TabPane` children
- Breadcrumb uses the `items` prop instead of `Breadcrumb.Item` children
- All menu items are objects with `key`, `label`, and optional `children` properties
- App is wrapped with `ConfigProvider` and `App` from antd for proper theme token access
- `Alert`: use `title` instead of deprecated `message` prop
- `Spin`: use `description` instead of deprecated `tip` prop
- `Tabs`: use `tabPlacement` instead of deprecated `tabPosition` prop
- `List` component is deprecated (replacement `Listy` not yet available); still usable for now

## TypeScript Configuration

- Target: ES2020
- Module: ESNext with bundler resolution
- Strict mode enabled
- JSX: react-jsx (React 19 automatic JSX transform)
- Types: vite/client, vitest/globals
- All source files in `src/` directory

## React 19 Patterns

**Key patterns:**
- Components must return `null` instead of `undefined`
- `ref` is now a regular prop (no need for `forwardRef` in most cases)
- Explicit `import React from 'react'` is not needed (automatic JSX transform)
- Use named imports: `import { useState, useEffect } from 'react'`
- Use plain function signatures instead of `React.FC<Props>`

## Routing

- Uses `react-router` v7 (import from `react-router`, not `react-router-dom`)
- `BrowserRouter` wraps the app in `App.tsx`
- `AppLayout` uses `<Routes>` and `<Outlet/>` for nested page routing
- Pages: `/` (HomePage), `/main` (MainPage), `/stations` (StationsPage)

## Testing

- Test runner: **Vitest** (not Jest) — 74 tests across 11 test files
- Use `vi.mock()`, `vi.fn()`, `vi.clearAllMocks()` etc.
- Test environment: jsdom
- Setup file: `src/setupTests.ts`
- Mocks Ant Design rc-component warnings in setup
- Test utilities and mock data in `src/test-utils/`
- Mock services (`MockRelayService`, `MockLocationService`) provide full CRUD stubs

## Backend Integration

The frontend is integrated with the relay-store-service backend at `http://localhost:8082`.

**Backend Services:**
- `RelayService` (`src/api/RelayService.ts`) - Full CRUD operations for relays
- `LocationService` (`src/api/LocationService.ts`) - Manage stations, track points, and crossings

**Data Fetching Pattern:**
```typescript
// Custom hook in src/hooks/useRelayData.ts
const {relays, stations, loading, error} = useRelayData();
```

## Development Notes

- The application is designed for deployment in Kubernetes with Tilt-based local development workflow
- Mock data mode available via `npm run start:mock` for development without backend
- Build tool: Vite (migrated from Create React App)
