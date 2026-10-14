# Better Badminton Vlaanderen

Companion app for the Badminton Vlaanderen portal. See `README.md` for what it is and how to run it.

## Plan before implement

Every feature or change goes through the **planner** agent first (`.claude/agents/planner.md`; start it with `/plan <issue number | URL | description>`). No production code is written without an approved plan. The planner intakes a single canonical ticket, runs the architecture review, resolves any ambiguity, breaks the work into verifiable tasks, and only then dispatches the `implement-change-*` agents. It plans and delegates; it never writes production code itself.

## Documentation rules

Keep docs short and true. Less is better; stale docs are worse than none.

- `README.md`: what it is, how to run it. Nothing else. Do not copy feature detail into it.
- `DOCUMENTATION.md`: the single place for feature specs, architecture and API, except Architecture Decision Records in `docs/adr/` and the diagram-as-code sources in `docs/architecture/`, which live outside it and are linked from it. Planned features are marked as such.
- Do not create new doc files unless asked. Do not add docs for things the code already shows.
- Tickets and requirements live in the GitHub Project (https://github.com/users/WoutDeleu/projects/9), not in the repo.

When a change affects any of these, update the doc in the same change:
- how to run, build or test the app -> `README.md`
- a feature's behavior, an API endpoint or the architecture -> the matching section in `DOCUMENTATION.md`
- a new or removed feature -> the feature line in `README.md` and its section in `DOCUMENTATION.md`

Before finishing a task, check that the run instructions in `README.md` still work and that nothing you changed contradicts `DOCUMENTATION.md`. Remove text that is no longer true instead of adding a note next to it.

## Architecture

Backend uses hexagonal architecture (`domain`, `application`, `infrastructure`), enforced by the ArchUnit tests in `backend/src/test/.../architecture`. Do not break them.

Architecture decisions are recorded as ADRs in `docs/adr/` — the one approved exception to keeping architecture in `DOCUMENTATION.md` — and linked from the Architecture Decisions section of `DOCUMENTATION.md`. The default is no architectural change; an ADR is added only when a structural change survives the architecture review and grilling.

The diagram-as-code sources (data-model UML and C4) live in `docs/architecture/` for the same reason — to stay diffable in review — and are linked from `DOCUMENTATION.md`. Keep them in sync whenever an ADR changes the architecture.
