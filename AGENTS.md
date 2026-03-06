# Repository Guidelines

## Project Structure & Module Organization

This is a multi-module workspace. Focus mainly on:
- `frontend/`: React + TypeScript app (UI code in `src/`, static files in `public/`, tests in `*.test.tsx`).
- `relay-store-service/`: Spring Boot service (`src/main/java` for app code, `src/test/java` for tests, Flyway SQL in `src/main/resources/db/migration`).

Keep changes scoped to one module unless API contracts require coordinated updates.

## Build, Test, and Development Commands

Run commands from each module directory.

- Frontend:
  - `npm install`: install dependencies.
  - `npm run dev`: start local Vite dev server.
  - `npm test`: run Vitest test suite.
  - `npm run build`: type-check and production build.
  - `npm run test:e2e -- --list`: validate Playwright test discovery.
- Relay store service:
  - `./gradlew test`: run unit/integration tests.
  - `./gradlew build`: full build pipeline.

## Coding Style & Naming Conventions

- TypeScript/React: 4-space indentation, PascalCase components (`StationsPage.tsx`), camelCase variables/functions, hooks prefixed with `use`.
- Java: 4-space indentation, PascalCase class names, layered packages (`core`, `db`, `web`, `config`).
- API pagination contract: use `PageResponse<T>` with `items` consistently across backend and frontend.

## Testing Guidelines

- Frontend: Vitest + Testing Library, colocated `*.test.tsx`.
- Backend: JUnit 5 + Mockito + Spring integration tests.
- Add or update tests for behavior changes and run both module test suites before PR.

## Commit & Pull Request Guidelines

- Use short imperative commit messages, optionally scoped (e.g., `frontend: normalize api error handling`).
- Keep commits focused and atomic.
- PR must include:
  - brief problem/solution summary,
  - changed modules,
  - test evidence (commands and results),
  - screenshots for UI changes.

## Security & Configuration Tips

- Do not commit secrets; use env variables.
- Review logging/config changes carefully (`application*.yml`).
- After dependency updates, rerun tests and build in both modules.
