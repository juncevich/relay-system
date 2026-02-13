# Relay Management System - Frontend

A React + TypeScript frontend application for managing relay hardware inventory. Built with Vite, React 19, and Ant
Design 6.

## Tech Stack

- **React 19** - UI framework
- **TypeScript 5** - Type safety
- **Vite 7** - Build tool and dev server
- **Ant Design 6** - UI component library
- **Vitest 4** - Testing framework
- **Axios 1.13** - HTTP client
- **React Router 7** - Routing

## Quick Start

### Development

```bash
# Install dependencies
npm install

# Start development server
npm start
# or
npm run dev

# Open http://localhost:3000
```

### Development with Mock Data

```bash
# Run with local mock data (no backend required)
npm run start:mock
```

This uses `VITE_USE_MOCK_DATA=true` to load data from `src/mock-data/` instead of calling the backend API.

### Building

```bash
# Build for production
npm run build

# Output is in build/ directory
```

### Preview Production Build

```bash
# Serve production build locally
npm run preview
```

### Testing

```bash
# Run tests once
npm test

# Run tests in watch mode
npm run test:watch
```

## Docker

### Build Docker Image

```bash
docker build -t alexunc/rs-frontend:0.0.1 .
```

### Run Docker Container

```bash
docker run -d --name rs_frontend -p3000:3000 alexunc/rs-frontend:0.0.1
```

## Environment Variables

Create a `.env` file for environment-specific configuration:

```bash
# API base URL (default: http://localhost:8082)
VITE_API_BASE_URL=http://localhost:8082

# Use mock data instead of backend (default: false)
VITE_USE_MOCK_DATA=true
```

All environment variables must be prefixed with `VITE_` to be accessible in the app.

## Project Structure

```
src/
├── api/              # API client services
├── components/       # React components
├── hooks/            # Custom React hooks
├── mock-data/        # Mock service implementations
├── models/           # Business logic models
├── pages/            # Page-level components
├── test-utils/       # Test utilities
├── types/            # TypeScript type definitions
├── App.tsx           # Root component
└── index.tsx         # Application entry point
```

## Available Scripts

| Command              | Description                              |
|----------------------|------------------------------------------|
| `npm start`          | Start development server on port 3000    |
| `npm run dev`        | Same as `npm start`                      |
| `npm run start:mock` | Start with mock data (no backend needed) |
| `npm run build`      | Build for production to `build/`         |
| `npm run preview`    | Preview production build locally         |
| `npm test`           | Run all tests once                       |
| `npm run test:watch` | Run tests in watch mode                  |

## Backend Integration

The frontend connects to the relay-store-service backend at `http://localhost:8082`.

Start the backend first:

```bash
cd ../relay-store-service
./gradlew bootRun
```

Then start the frontend:

```bash
npm start
```

## Documentation

- **[CLAUDE.md](./CLAUDE.md)** - Development guide and architecture details
- **[BACKEND_INTEGRATION.md](./BACKEND_INTEGRATION.md)** - API integration documentation
- **[TESTING.md](./TESTING.md)** - Testing patterns and best practices

## Learn More

- [React Documentation](https://react.dev/)
- [Vite Documentation](https://vite.dev/)
- [Ant Design Documentation](https://ant.design/)
- [TypeScript Documentation](https://www.typescriptlang.org/)
