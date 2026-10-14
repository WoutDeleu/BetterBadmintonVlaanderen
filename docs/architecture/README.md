# Architecture diagrams

Diagram-as-code sources for the architecture described in
[`DOCUMENTATION.md`](../../DOCUMENTATION.md). They live here — outside `DOCUMENTATION.md` —
so they stay **diffable** in review; this is an approved exception to the single-source docs
rule, alongside [`docs/adr/`](../adr/). `DOCUMENTATION.md` remains the narrative home and
links to these files.

## Format

[Mermaid](https://mermaid.js.org/) — it renders natively on GitHub, needs no toolchain, and
diffs cleanly. Two concerns, two files:

| File | What | Mermaid type |
|---|---|---|
| [`data-model.md`](data-model.md) | Domain / data model (UML) | `classDiagram` |
| [`c4.md`](c4.md) | System context, containers, components | `C4Context` / `C4Container` / `C4Component` |

## Convention

- Keep each diagram in a fenced ```mermaid block so GitHub renders it.
- These diagrams describe the **current** architecture only: a hexagonal Spring Boot
  monolith with PostgreSQL, a React + TypeScript frontend, and the external Badminton
  Vlaanderen portal (see `docker-compose.yml`). Do not draw services, brokers or datastores
  that do not exist.
- The **architect** agent (`.claude/agents/architect.md`) updates these in the same change as
  the ADR whenever an architecture decision is accepted. Keep them true; delete what is no
  longer accurate rather than annotating it.
