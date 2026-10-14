# Code review rules

Review only. Never change production code, tests, requirements or quality gates. Report evidence, not guesses about intent. `AGENTS.md` also applies.

## Scopes and domains
FE (`frontend/`), BE (`backend/`) and Mobile (`mobile/`, not created yet) are review scopes, not bounded contexts. Business domains, from `DOCUMENTATION.md`: ranking simulation, tournaments (draws, predictions), competition scenarios, club management (interclub teams), favorites dashboard, accounts, external links. Use these terms. Planned features are not implemented behavior; do not review code against a spec that is only planned unless a ticket says to build it.

## Requirements
Trace changed behavior to the linked issue's acceptance criteria and the matching `DOCUMENTATION.md` section. If neither exists or they conflict, report that instead of inventing a requirement.

## Domain model
Prefer validated, immutable value objects over raw strings, numbers and long primitive parameter lists for domain concepts (ids, ranking and index values, dates and ranges, scores). Check validation at construction, equality, units, serialization and conversion at boundaries. Do not demand wrappers where no invalid state or mix-up is possible.

## Tests
Compare tests and test, build and CI config against the base branch. Flag removed or weakened assertions, changed expected values, disabled or skipped tests, narrower test discovery, widened tolerances, over-mocking, and exclusions of architecture or business rules. A behavior-changing test edit needs an approved requirement, not just a changed implementation.

## Warnings
Zero compilation, type and lint warnings. Flag suppressions, loosened compiler settings and excluded files added to reach that.

## Limits
This review cannot run the build or tests. Say what CI evidence exists on the PR; if there is none, state that tests, warnings and runtime behavior were not verified. Never write PASS for what was not shown.

## Output
Per finding: severity, scope (FE/BE/Mobile), file:line, violated rule or requirement, evidence, impact, fix. End with: verdict (approve / changes required / not verifiable), and what was not verified.
