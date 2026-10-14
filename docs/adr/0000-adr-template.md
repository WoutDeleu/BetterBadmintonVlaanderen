# NNNN. <Short decision title>

- **Status:** Proposed <!-- Proposed | Accepted | Superseded by NNNN -->
- **Date:** YYYY-MM-DD
- **Ticket:** <#issue or URL on Project #9>
- **Deciders:** <who approved — leave <PLACEHOLDER> if unknown>

## Context and problem statement

What is forcing a decision now? State the problem, the constraints, and the part of the
existing architecture under pressure (hexagonal `domain`/`application`/`infrastructure`
boundaries and the ArchUnit rules, the FE/Mobile instructions, the relevant
`DOCUMENTATION.md` section). The starting position is that the current architecture stands
and no change is needed; say why that position is in question.

## Decision drivers

- <A concrete, present force — not "it'd be cleaner" or "we might need to scale".>
- <The cost of doing nothing.>
- <Constraints: team, operability, reversibility, portal integration, etc.>

## Options considered

- **Keep the existing architecture (default):** why it was or was not enough.
- **<Option A>:** trade-offs.
- **<Option B>:** trade-offs.

## Decision

The chosen option, in one or two sentences, and why it beats the others. Be specific about
what moves, what boundary changes, and what stays the same.

## Consequences

- **Positive:** what this unlocks or simplifies.
- **Negative / costs:** new complexity, migration, risk to ArchUnit boundaries.
- **Follow-up:** docs to update (`DOCUMENTATION.md`, `AGENTS.md`, instructions), diagrams to
  update (`docs/architecture/data-model.md`, `docs/architecture/c4.md`), tests to add.

## Grilling record

Summary of the `grill-me` challenge that justified accepting this change: the sharpest
objections raised and why the change still holds. An ADR that cannot show it survived
grilling should stay `Proposed`, not `Accepted`.
