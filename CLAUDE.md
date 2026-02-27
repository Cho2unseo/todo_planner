# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Run the application
./gradlew bootRun

# Build
./gradlew build

# Run all tests
./gradlew test

# Run a specific test class
./gradlew test --tests "org.topl.spring.ApplicationTests"

# Clean build
./gradlew clean build
```

## Environment Setup

The app uses `dotenv-java` to load a `.env` file at startup. Create a `.env` file in the project root with:

```
DB_URL=jdbc:mysql://localhost:3306/<database>
DB_USERNAME=<username>
DB_PASSWORD=<password>
JWT_SECRET=<secret>
```

## Architecture

Spring Boot 3 / Java 17 REST API with JWT-based stateless authentication.

**Package root**: `org.topl.spring`

**Module structure** (feature-based):
- `common/` — Shared utilities: JWT auth (`auth/`), global exception handling (`exception/`), API response wrapper (`response/`), enums (`status/`)
- `user/` — User registration, login, profile
- `todo/` — Todo CRUD
- `config/SecurityConfig.java` — Spring Security + JWT filter chain

**Layering within each module**: `controller → service → repository → entity`, with `dto/request/` and `dto/response/` as Java records.

**API response convention**: All endpoints return `ApiResponse<T>` wrapping a `DataHeader` (status/message) and optional data payload.

**Authentication flow**:
1. `POST /api/users/login` returns a JWT access token (1h) and refresh token (7d)
2. Subsequent requests include `Authorization: Bearer <token>`
3. `JwtAuthenticationFilter` validates the token and sets a `CustomUser` principal (extends Spring's `User`, adds `userId`)
4. Public routes: `POST /api/users/signup`, `POST /api/users/login`

## Commit Message Convention

Format: `[<gitmoji> <TAG>] 메시지`

| Gitmoji | Tag | Use |
|---------|-----|-----|
| ✨ | FEAT | New feature |
| 🐛 | FIX | Bug fix |
| ♻️ | REFACTOR | Refactoring |
| 📝 | DOCS | Documentation |
| ✅ | TEST | Tests |
| 💄 | STYLE | Formatting |
| ⚡️ | PERF | Performance |
| 🚀 | CHORE | Other |
