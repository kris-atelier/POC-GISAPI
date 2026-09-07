# POC-GISAPI

[![CI](https://github.com/kris-atelier/POC-GISAPI/actions/workflows/ci.yml/badge.svg)](https://github.com/kris-atelier/POC-GISAPI/actions/workflows/ci.yml)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A Kotlin/Spring Boot proof of concept for serving spatial polygon data from PostgreSQL + PostGIS over HTTP.

## What it demonstrates

- Spring Boot REST API written in Kotlin
- PostgreSQL + PostGIS persistence
- Hibernate Spatial / JTS geometry mapping
- Paginated polygon retrieval
- Swagger UI for API exploration
- Docker-based local environment
- GitHub Actions validation with a real PostGIS service and runtime API smoke test

For the smaller Spring Boot starter used before this end-to-end example, see [poc-gis-spring](https://github.com/kris-atelier/poc-gis-spring).

## Requirements

### Easiest path

- Docker + Docker Compose

### Local JVM path

- JDK 17
- PostgreSQL with PostGIS enabled

## Quick start with Docker

```bash
docker compose up --build
```

Then open:

- API: `http://localhost:8080/Polygon/GetAll`
- Swagger UI: `http://localhost:8080/swagger-ui/`

The Docker stack starts both PostGIS and the API with development-only credentials.

## Local run

Configure the database with environment variables if needed:

```bash
export DB_URL=jdbc:postgresql://localhost:5432/GIS
export DB_USERNAME=postgres
export DB_PASSWORD=postgres
```

Build and run:

```bash
./gradlew clean build -x test
java -jar build/libs/*.jar
```

## Configuration

| Variable | Default | Purpose |
| --- | --- | --- |
| `DB_URL` | `jdbc:postgresql://localhost:5432/GIS` | JDBC connection URL |
| `DB_USERNAME` | `postgres` | Database user |
| `DB_PASSWORD` | `postgres` | Database password for local POC use |
| `JPA_DDL_AUTO` | `update` | Hibernate schema mode |
| `JPA_SHOW_SQL` | `false` | SQL logging |

Do not reuse the example database credentials outside local development.

## Endpoint

### `GET /Polygon/GetAll`

Returns paginated polygon entities from the configured PostGIS database. Standard Spring pagination query parameters such as `page`, `size`, and `sort` can be used.

Example:

```bash
curl 'http://localhost:8080/Polygon/GetAll?page=0&size=20'
```

## Verification

GitHub Actions validates the complete POC path:

1. Starts PostgreSQL with PostGIS.
2. Runs the Gradle test/build lifecycle on JDK 17.
3. Builds the application Docker image.
4. Starts the application container.
5. Calls `/Polygon/GetAll?page=0&size=1` as a runtime smoke test.

This keeps the public example continuously checked for both build-time and basic runtime regressions.

## Scope

This repository is intentionally a proof of concept rather than a production-ready GIS service. Authentication, database migrations, production observability, rate limiting and hardened deployment configuration are out of scope.

Licensed under the MIT License.
