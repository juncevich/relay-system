# Relay Store Service

Spring Boot service for relay inventory, storage, location, and movement APIs.

## Stack

- Spring Boot 4.0.4
- Gradle 9.4.1 wrapper
- Flyway 12.1.1
- Testcontainers 2.0.4
- MapStruct 1.6.3
- Lombok 1.18.44
- PostgreSQL and H2

## Commands

```bash
./gradlew test
./gradlew build
./gradlew dependencyUpdates
```

When running inside restricted environments, use:

```bash
GRADLE_USER_HOME=$PWD/.gradle-home ./gradlew test
```

## Verification

The current dependency set was verified with:

```bash
GRADLE_USER_HOME=$PWD/.gradle-home ./gradlew test --warning-mode all
```

## Dependency Notes

- Spring Boot, Flyway, Testcontainers, and Lombok were updated to the latest versions available from the current Gradle
  dependency report.
- `commons-beanutils` was removed. The custom snowflake ID generator now reads the `id` field directly instead of using
  reflective bean property utilities.
- The Gradle wrapper is updated to `9.4.1`.

## Related Docs

- [CLAUDE.md](./CLAUDE.md)
- [requests/get_all_relays.http](./requests/get_all_relays.http)
