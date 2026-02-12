# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
./gradlew assemble              # Build JAR
./gradlew check                 # Run all tests + JaCoCo coverage report
./gradlew test                  # Run all tests
./gradlew bootRun               # Run application (uses dev profile by default)
./gradlew jibDockerBuild        # Build Docker image locally
./gradlew jacocoTestReport sonarqube  # Coverage + SonarCloud analysis
```

### Running specific tests

```bash
./gradlew test --tests "com.relay.unit.service.RelayServiceTest"           # Single test class
./gradlew test --tests "com.relay.unit.service.RelayServiceTest.methodName" # Single test method
```

Tests use JUnit 5 with `@Tag("unit")` for unit tests and `@Tag("integration")` for integration tests. Unit tests extend
`GenericUnitTest`. Integration tests extend `AbstractDBTest` (TestContainers with PostgreSQL).

## Architecture

This is a Spring Boot 4.0.2 microservice (Java 25) for managing relay hardware inventory. It uses virtual threads (
`spring.threads.virtual.enabled: true`).

### Layer structure

```
com.relay
├── web/                      # REST layer
│   ├── controllers/          # @RestController endpoints (5 controllers)
│   ├── dto/                  # Request/response records
│   │   ├── location/         # Station, TrackPoint, Crossing DTOs
│   │   ├── storage/          # Warehouse, Stand, RelayCabinet, Shelf DTOs
│   │   └── history/          # RelayMovement DTOs
│   ├── model/                # API response models
│   │   ├── location/         # StationResponse, TrackPointResponse, CrossingResponse
│   │   ├── storage/          # WarehouseResponse, StandResponse, RelayCabinetResponse, ShelfResponse
│   │   └── history/          # RelayMovementResponse
│   ├── mappers/              # MapStruct mappers (core model -> web response)
│   └── exceptions/           # @ControllerAdvice exception handler, custom exceptions
├── core/
│   ├── model/                # Core business model records
│   │   ├── location/         # Crossing, Station, TrackPoint
│   │   ├── storage/          # Shelf, RelayCabinet, Stand, Warehouse
│   │   └── history/          # RelayMovement
│   └── service/              # Business logic (@Service, @Transactional)
├── db/
│   ├── entity/               # JPA entities
│   │   ├── items/            # Relay, RelayType enum
│   │   ├── location/         # Location (superclass), Station, TrackPoint, Crossing
│   │   ├── storage/          # Storage (superclass), Warehouse, Stand, RelayCabinet, Shelf
│   │   └── history/          # RelayMovement
│   ├── dao/                  # Spring Data JPA repositories (JpaRepository interfaces)
│   ├── repository/           # Repository layer (wraps DAOs, handles entity<->core model mapping)
│   └── mappers/              # MapStruct mappers (entity <-> core model)
├── infrastructure/           # Snowflake ID generation, custom annotations (@EntityId)
└── config/                   # Spring configuration (DB, OpenAPI, logging)
```

### Key patterns

- **Three-layer model separation**: JPA Entity (`db.entity`) -> Core Model (`core.model`) -> Web Response (
  `web.model`), each mapped via dedicated MapStruct mappers
- **Three-layer repository pattern**: DAO (`db.dao`) -> Repository (`db.repository`) -> Service (`core.service`).
  DAOs extend `JpaRepository`, Repositories wrap DAOs and handle entity-model mapping, Services contain business logic
- **Entity inheritance**: JOINED table inheritance for Location (Station, TrackPoint, Crossing) and Storage (Warehouse,
  Stand, RelayCabinet) hierarchies
- **Snowflake IDs**: Custom `@EntityId` annotation triggers `SnowflakeEntityIdGenerator` for distributed ID generation
- **JPA Auditing**: `@CreatedDate` / `@LastModifiedDate` on entities, enabled via `@EnableJpaAuditing` in `DbConfig`
- **Optimistic locking**: `@Version` field on entities
- **Validation**: Jakarta Bean Validation on request DTOs (e.g., `@Size(min=5, max=10)` on serialNumber)
- **Nullability**: JSpecify 1.0.0 annotations (`@NonNull`, `@Nullable`)
- **Exception handling**: `CustomizedResponseEntityExceptionHandler` extends `GlobalExceptionHandler` (from internal
  library), returns RFC 7807 ProblemDetail responses. Custom exceptions: `EntityNotFoundException`,
  `RelayNotFoundException`, `ShelfNotFoundException`, `StorageNotFoundException`, `LocationNotFoundException`,
  `InvalidBusinessStateException`, `BusinessException`

### Database

- **Production**: PostgreSQL with Flyway migrations in `src/main/resources/db/migration/`
- **Dev profile** (`application-dev.yml`): PostgreSQL on `localhost:5432/relay-service`, port 8082
- **Tests**: H2 in-memory or TestContainers PostgreSQL

### Active REST endpoints

#### RelayController (`/relays`)

| Method | Path                                   | Description                |
|--------|----------------------------------------|----------------------------|
| GET    | `/relays`                              | Get all relays (paginated) |
| GET    | `/relays/{id}`                         | Get relay by ID            |
| GET    | `/relays/serial-number/{serialNumber}` | Find by serial number      |
| GET    | `/relays/by-creation-date?date=`       | Find by creation date      |
| GET    | `/relays/by-last-check-date?date=`     | Find by last check date    |
| POST   | `/relays`                              | Create relay               |
| PUT    | `/relays/{id}`                         | Update relay               |
| DELETE | `/relays/{id}`                         | Delete relay               |

#### LocationController (`/stations`, `/track-points`, `/crossings`)

Full CRUD (GET all paginated, GET by ID, POST, PUT, DELETE) for each of:

- `/stations` — railway stations
- `/track-points` — track points
- `/crossings` — crossings

#### StorageController (`/warehouses`, `/stands`, `/relay-cabinets`)

Full CRUD (GET all paginated, GET by ID, POST, PUT, DELETE) for each of:

- `/warehouses` — warehouses
- `/stands` — stands
- `/relay-cabinets` — relay cabinets

#### ShelfController (`/shelves`)

Full CRUD (GET all paginated, GET by ID, POST, PUT, DELETE).

#### RelayMovementController (`/relay-movements`)

| Method | Path                               | Description                   |
|--------|------------------------------------|-------------------------------|
| GET    | `/relay-movements`                 | Get all movements (paginated) |
| GET    | `/relay-movements/{id}`            | Get movement by ID            |
| GET    | `/relay-movements/relay/{relayId}` | Get movements by relay ID     |
| POST   | `/relay-movements`                 | Record a relay movement       |
| DELETE | `/relay-movements/{id}`            | Delete movement record        |

### Dependencies requiring annotation processing

Both Lombok and MapStruct require annotation processors. The build configures:

- `annotationProcessor "org.projectlombok:lombok"`
- `annotationProcessor "org.mapstruct:mapstruct-processor:$mapStructVersion"`

IDEs must have annotation processing enabled.

### Internal library dependency

The project depends on `ru.relay.infrastructure:rest:0.0.1` from Maven Local. This must be installed locally before
building.

## Testing

- **Unit tests** (`com.relay.unit`): Extend `GenericUnitTest`, tagged `@Tag("unit")`
  - `unit/web/` — Controller tests
  - `unit/db/` — Repository tests
  - `unit/service/` — Service tests
- **Integration tests** (`com.relay.integration`): Extend `AbstractDBTest`, tagged `@Tag("integration")`
  - Uses TestContainers with PostgreSQL, `@SpringBootTest`, `@AutoConfigureTestDatabase`
- **Frameworks**: JUnit 5, AssertJ 3.27.7, Spring Boot Test, JaCoCo 0.8.14

## CI/CD

GitLab CI pipeline (`.gitlab-ci.yml`): build -> test -> analyze (SonarCloud). Containerization via Google JIB plugin or
multi-stage Dockerfile (Eclipse Temurin 25, Alpine). Kubernetes deployment config in `k8s/deployment.yaml`.
Swagger UI available at `/swagger-ui.html` (SpringDoc OpenAPI 3.0.1).