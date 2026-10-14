---
name: board-status
description: Move a GitHub Project #9 issue between Status values.
model: claude-haiku-5-5
disable-model-invocation: true
allowed-tools: Bash(gh project item-*) Bash(gh api graphql *)
---

Move a ticket's **Status** in GitHub Project #9.

## Inputs
- issue number (or issue URL)
- target Status value label (Backlog, Refined, In Progress, Blocked, In Review, Done)

## Status mapping (Project #9)
- Status field id: `PVTSSF_lAHOBMzHZc4Bm0luzhlb_2c`
- Options:
  - Backlog: `f75ad846`
  - Refined: `f15818ec`
  - In Progress: `47fc9ee4`
  - Blocked: `89b6f64c`
  - In Review: `44af7b33`
  - Done: `98236657`
  
(If GitHub regenerates these ids, refresh via the fallback step.)

## Workflow
1. Resolve the GitHub issue -> project item id.
2. Resolve the Status field id + option id(s) for the given label.
   - If an edit fails because ids changed, refresh the field/option ids and retry once.
3. Run `gh project item-edit` to set the status.

## Rules
- Do not change other fields.
- If the status label is unknown, ask for clarification.
- If the issue has no project item in Project #9, stop and report the issue is not on the board.
