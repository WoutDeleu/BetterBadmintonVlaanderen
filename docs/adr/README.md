# Architecture Decision Records (ADRs)

This directory is the home for Architecture Decision Records. It is the **one approved
exception** to the rule in `AGENTS.md` that architecture lives in `DOCUMENTATION.md`:
decisions are recorded here and linked from the *Architecture Decisions* section of
`DOCUMENTATION.md`.

## When an ADR is written

The default is **no architectural change**. An ADR is drafted only inside the planner
workflow (see `.claude/agents/planner.md`), when the `architecture-reviewer` agent judges
that a feature genuinely requires a structural change, and only kept if that change
survives grilling (`.claude/skills/grill-me/SKILL.md`). A rejected change is not recorded
as an ADR; the planner records "no architectural change" and continues.

## Convention

- Copy `0000-adr-template.md` to `NNNN-short-kebab-title.md`, using the next free
  four-digit number (`0001`, `0002`, ...).
- Fill in **context, decision drivers, options considered, decision, consequences**.
- Set the status (`Proposed` while under review, `Accepted` once it survives grilling,
  `Superseded by NNNN` when replaced). Do not delete superseded ADRs.
- Add a link to the accepted ADR in the *Architecture Decisions* section of
  `DOCUMENTATION.md`.
