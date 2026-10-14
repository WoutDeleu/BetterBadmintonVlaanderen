# Claude Code AI setup — answer sheet

Copy/paste your answers here (or edit in-place), then tell me when you’re done and I’ll generate the concrete fix plan.

## A) Model strategy for `model: inherit`
Choose **one**:
- [x] A1) Pin every agent that says `model: inherit` to an explicit Claude Code model id.
- [ ] A2) Add a deterministic default model/provider in `.claude/settings.json` so `inherit` resolves consistently.

## B) Which model id(s) for each agent (only if A1 is selected)
For each agent, choose one: `opus` / `sonnet` / `haiku`.
- rule-researcher: opus
- refinement: opus
- scrape-definition: sonet
- software-architect: opus
- spring-boot-hexagonal-backend: sonnet
- business-analyst-requirements: opus
- product-owner-strategic: opus

If you’re unsure, you can write `not sure yet`.

## C) Should skills be pinned too?
- [x] C1) Yes — add `model: opus|sonnet|haiku` to skills where missing.
- [ ] C2) No — only agents must be deterministic.

## D) Missing PDF extraction path (`<PDF_READER>`)
Choose **one**:
- [x] D1) Implement a new `.claude/skills/pdf-reader` skill and update `rule-researcher` to call it.
- [ ] D2) Update `rule-researcher` to use an existing approved PDF/text extraction approach already present in this repo.
- [ ] D3) not sure yet

## E) Broken skill reference: `board-status`
Choose **one**:
- [x] E1) Implement `.claude/skills/board-status`.
- [ ] E2) Remove/adjust the reference in `.claude/skills/create-ticket/SKILL.md` so there are no broken mentions.
- [ ] E3) not sure yet

## F) Can we verify live reglement/toernooi data from this environment?
Choose **one**:
- [x] F1) Yes
- [ ] F2) No (no web access from here)
- [ ] F3) not sure yet

---

## Notes / constraints
Anything you want me to respect (cost limits, preferred models, “don’t touch X”, etc.):

- 
