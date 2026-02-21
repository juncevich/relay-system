# Fullstack Architect Memory

## Project Architecture

- Monorepo: frontend (React 19 + Vite 7 + AntD 6) + relay-store-service (Spring Boot 4 + Java 25)
- Backend layers: web (controllers/dto/model/mappers/exceptions) -> core (service/model/exceptions) -> db (
  entity/dao/repository/mappers)
- Three-model separation: JPA Entity <-> Core Model <-> Web Response, each mapped via MapStruct
- Repository layer wraps DAO (JpaRepository) and handles entity<->model mapping
- Frontend API layer: service classes (RelayService, LocationService, StorageService) use axios singleton
- Mock/Real service switching via `services/index.ts` based on `VITE_USE_MOCK_DATA`
- Custom hook `useRelayData` fetches all data in parallel with race condition prevention

## Key Issues Found (Feb 2026)

- See `architecture-review.md` for full review
- Backend: duplicate exception hierarchies (core + web), no CORS config, Location entity uses @GeneratedValue instead of
  Snowflake, @Disabled integration test
- Frontend: no 404 route, no axios interceptors, hardcoded image URL, singleton service anti-pattern, no pagination UI
- Cross-cutting: no shared API contract, inconsistent field naming (lastCheckDate vs verificationDate)

## File Paths

- Frontend entry: `frontend/src/App.tsx`
- Frontend types: `frontend/src/types/relay.types.ts`
- Frontend hook: `frontend/src/hooks/useRelayData.ts`
- Backend main: `relay-store-service/src/main/java/com/relay/RelaySystemApplication.java`
- Backend exception handler:
  `relay-store-service/src/main/java/com/relay/web/exceptions/CustomizedResponseEntityExceptionHandler.java`
- DB migration: `relay-store-service/src/main/resources/db/migration/V1__init.sql`
