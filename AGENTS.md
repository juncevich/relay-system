# Repository Guidelines

## Quick Start Checklist

1. Install dependencies:
    - `cd frontend && npm install`
2. Verify local quality gates:
    - `cd frontend && npm test && npm run build`
    - `cd ../relay-store-service && ./gradlew test`
3. Make focused changes in one module at a time.
4. Re-run the relevant test/build commands before opening a PR.
5. Include test evidence and screenshots (for UI changes) in the PR description.

## Project Structure & Module Organization

This repository is a multi-service workspace.

- `frontend/`: React 19 + Vite + TypeScript UI (`src/`, `public/`, tests in `*.test.tsx`).
- `relay-store-service/`: Spring Boot service (`src/main/java`, `src/test/java`, Flyway migrations in
  `src/main/resources/db/migration`).
- Other sibling directories (`chat-service/`, `location-service/`, etc.) follow similar service-oriented layout.
- Infra and deployment assets live in `k8s/`, `helm/`, `docker-compose.yml`, and `skaffold.yaml`.

## Build, Test, and Development Commands

Run commands from each module directory unless noted.

- Frontend:
    - `cd frontend && npm install` - install dependencies.
    - `npm run dev` - start local Vite dev server.
    - `npm test` - run Vitest test suite once.
    - `npm run build` - type-check and create production bundle.
- Relay store service:
    - `cd relay-store-service && ./gradlew test` - run JUnit tests.
    - `./gradlew build` - compile, test, and package.

## Coding Style & Naming Conventions

- TypeScript/React: 4-space indentation, PascalCase components (`MainTab.tsx`), camelCase vars/functions, hooks prefixed
  with `use` (`useRelayData`).
- Java: 4-space indentation, package-by-layer (`core`, `db`, `web`, `config`), classes in PascalCase, tests ending with
  `Test`/`ITTest`.
- Keep DTO/model mapping explicit (MapStruct in backend). Prefer small, composable React components.

## Testing Guidelines

- Frontend uses Vitest + Testing Library (`*.test.tsx` beside source).
- Backend uses JUnit 5, Mockito, and integration tests in `src/test/java/com/relay/integration`.
- Add or update tests for behavioral changes; verify with:
    - `frontend: npm test`
    - `relay-store-service: ./gradlew test`

## Commit & Pull Request Guidelines

- Follow current history style: concise, imperative summaries; optional module prefix when scoped (e.g.,
  `relay-store-service: harden validation`).
- Keep commits focused (one logical change per commit).
- PRs should include:
    - What changed and why.
    - Affected modules/paths.
    - Test evidence (command + result).
    - UI screenshots for frontend visual changes.

## Security & Configuration Tips

- Do not commit secrets. Use environment variables for tokens/credentials.
- Review CORS and logging changes carefully in backend config.
- Prefer dependency updates via lockfiles and run tests after upgrades.
