---
name: create-ticket
description: Create a ticket for Better Badminton Vlaanderen. Creates a real GitHub issue in WoutDeleu/BetterBadmintonVlaanderen, adds it to GitHub Project #9, and sets Kind and Area. Use when asked to create, add or file a ticket, issue, epic, feature, user story, task or bug.
disable-model-invocation: true
model: claude-sonnet-5-5
argument-hint: <title or short description>
allowed-tools: Bash(gh issue *) Bash(gh project *) Bash(gh repo view *)
---

Create a ticket for: $ARGUMENTS

A ticket is always all three: a real **issue** in the repo, **added to the project**, with its **fields set**. Never create a project draft item, and never an issue that is not on the board.

Fixed values:
- Repo: `WoutDeleu/BetterBadmintonVlaanderen`
- Project: number `9`, owner `WoutDeleu` (id `PVT_kwHOBMzHZc4Bm0lu`)

## Allowed kinds

Only these five. Do not invent others (no Spec, no Chore, no Spike).

| Kind | Color | Use for |
|---|---|---|
| Epic | purple | Large goal that groups several features |
| Feature | blue | A user-facing capability |
| User Story | green | "As a <user>, I want <goal>, so that <reason>" |
| Task | yellow | A technical unit of work |
| Bug | red | Something that is broken |

Hierarchy: Epic > Feature > User Story > Task. Link a child to its parent (see step 5) when the parent is known. Bugs stand alone.

## 1. Decide the content

Pick from the request; ask the user only if you cannot tell:
- **Title**: short. A User Story uses the "As a ..., I want ..." form in the body, with a short title.
- **Body**: what and why, plus acceptance criteria as a checklist. No filler. Link to the relevant section of `DOCUMENTATION.md` instead of copying it.
- **Kind**: one of the five above
- **Area**: Backend, Frontend, Mobile, Infra, Docs or AI-config
- **Label**: Bug -> `bug`, Feature/User Story -> `enhancement`, Area Docs -> `documentation`; otherwise none

Check for duplicates first: `gh issue list --repo WoutDeleu/BetterBadmintonVlaanderen --search "<keywords>" --state all`. If a match exists, tell the user instead of creating another.

## 2. Create the issue

```bash
gh issue create --repo WoutDeleu/BetterBadmintonVlaanderen \
  --title "<title>" --body "<body>" [--label "<label>"]
```
This prints the issue URL. Keep it.

## 3. Add it to the project

```bash
gh project item-add 9 --owner WoutDeleu --url <issue-url> --format json
```
Keep the `id` from the output (the project item id, starts with `PVTI_`).

## 4. Set the fields

One command per field, with the item id from step 3. Status starts as Backlog; leave it unless asked.

```bash
gh project item-edit --id <item-id> --project-id PVT_kwHOBMzHZc4Bm0lu \
  --field-id <field-id> --single-select-option-id <option-id>
```

| Field | Field id | Options (option id) |
|---|---|---|
| Kind | `PVTSSF_lAHOBMzHZc4Bm0luzhlcASc` | Epic `07137211`, Feature `d8142abe`, User Story `003e3e06`, Task `c32eba79`, Bug `502c07dc` |
| Area | `PVTSSF_lAHOBMzHZc4Bm0luzhlcADk` | Backend `67280116`, Frontend `4ef95b5d`, Mobile `ba35ee43`, Infra `c878c4fc`, Docs `5a1c9dd8`, AI-config `4bb5f14b` |
| Status | `PVTSSF_lAHOBMzHZc4Bm0luzhlb_2c` | Backlog `f75ad846`, Refined `f15818ec`, In Progress `47fc9ee4`, Blocked `89b6f64c`, In Review `44af7b33`, Done `98236657` (moving status is the `board-status` skill: `.claude/skills/board-status/SKILL.md`) |

There is no Priority field.

If an edit fails with a not-found error, the board changed (editing a field regenerates its option ids). Refresh with:
```bash
gh project field-list 9 --owner WoutDeleu --format json
```
and update this file.

## 5. Link to a parent (only if there is one)

```bash
gh api graphql -f query='mutation($p:ID!,$c:ID!){addSubIssue(input:{issueId:$p,subIssueId:$c}){issue{number}}}' \
  -f p=<parent-issue-node-id> -f c=<child-issue-node-id>
```
Get a node id with `gh issue view <number> --repo WoutDeleu/BetterBadmintonVlaanderen --json id --jq .id`.

## 6. Report

Give the user the issue URL and the Kind and Area you set. Do not edit docs or code as part of creating a ticket.
