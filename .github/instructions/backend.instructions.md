---
applyTo: "backend/src/main/**"
---
Hexagonal architecture: `domain`, `application`, `infrastructure`, enforced by the ArchUnit tests in `backend/src/test/**/architecture`.
- Domain depends on nothing outside itself: no Spring, JPA, HTTP or serialization types. Domain models are immutable, without setters.
- Ports are interfaces owned by the application layer; adapters live in infrastructure. Application never imports infrastructure.
- Business rules belong in the domain, not in controllers, repositories or mappers. Transaction boundaries sit on application use cases and keep each aggregate consistent.
- Convert to and from API and persistence types at the boundary; value objects validate on construction.
- Flag changes that add exclusions to, or weaken, the ArchUnit tests.
