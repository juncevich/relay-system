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

Tests use JUnit 5 with `@Tag("unit")` for unit tests. Unit tests extend `GenericUnitTest`. Integration tests use
TestContainers with PostgreSQL.

## Architecture

This is a Spring Boot 4.0.0 microservice (Java 25) for managing relay hardware inventory. It uses virtual threads (
`spring.threads.virtual.enabled: true`).

### Layer structure

```
com.relay
├── web/               # REST layer
│   ├── controllers/   # @RestController endpoints
│   ├── dto/           # Request/response records (CreateRelayRequest, CreateRelayResponse)
│   ├── model/         # API domain models (Relay, District, Stage, etc.)
│   └── exeptions/     # @ControllerAdvice exception handler, ProblemDetail responses
├── core/
│   └── service/       # Business logic (@Service, @Transactional)
├── db/
│   ├── entity/        # JPA entities (items/Relay, RelayType enum)
│   ├── repository/    # Spring Data JPA repositories
│   └── mappers/       # MapStruct mappers (entity <-> model conversion)
├── infrastructure/    # Snowflake ID generation, custom annotations (@EntityId)
└── config/            # Spring configuration classes (DB, OpenAPI, logging)
```

### Key patterns

- **Entity vs Model separation**: `db.entity.items.Relay` (JPA entity) and `web.model.Relay` (API model) are distinct
  classes mapped via `RelayMapper` (MapStruct with `componentModel = "spring"`)
- **Snowflake IDs**: Custom `@EntityId` annotation triggers `SnowflakeEntityIdGenerator` for distributed ID generation
- **JPA Auditing**: `@CreatedDate` / `@LastModifiedDate` on entities, enabled via `@EnableJpaAuditing` in `DbConfig`
- **Optimistic locking**: `@Version` field on Relay entity
- **Validation**: Jakarta Bean Validation on request DTOs (e.g., `@Size(min=5, max=10)` on serialNumber)
- **Exception handling**: `CustomizedResponseEntityExceptionHandler` returns RFC 7807 ProblemDetail responses

### Database

- **Production**: PostgreSQL with Flyway migrations in `src/main/resources/db/migration/`
- **Dev profile** (`application-dev.yml`): PostgreSQL on `localhost:5432/relay-service`, port 8082
- **Tests**: H2 in-memory or TestContainers PostgreSQL

### Active REST endpoints

| Method | Path                                | Description                |
|--------|-------------------------------------|----------------------------|
| GET    | `/relays`                           | Get all relays (paginated) |
| GET    | `/relay/{id}`                       | Get relay by ID            |
| GET    | `/relay/serialNumber?serialNumber=` | Find by serial number      |
| POST   | `/relay`                            | Create relay               |

### Dependencies requiring annotation processing

Both Lombok and MapStruct require annotation processors. The build configures:

- `annotationProcessor "org.projectlombok:lombok"`
- `annotationProcessor "org.mapstruct:mapstruct-processor:$mapStructVersion"`

IDEs must have annotation processing enabled.

### Internal library dependency

The project depends on `ru.relay.infrastructure:rest:0.0.1` from Maven Local. This must be installed locally before
building.

## CI/CD

GitLab CI pipeline (`.gitlab-ci.yml`): build -> test -> analyze (SonarCloud). Containerization via Google JIB plugin or
multi-stage Dockerfile. Kubernetes deployment config in `k8s/deployment.yaml`.
