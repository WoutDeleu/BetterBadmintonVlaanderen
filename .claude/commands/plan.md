---
description: Plan a feature or change before implementation — starts the planner agent.
argument-hint: <issue number | issue URL | freeform description>
---

Start the **planner** agent (`.claude/agents/planner.md`) on the following input, handed to
it as-is:

$ARGUMENTS

The planner will intake a single canonical ticket (loading the GitHub issue when given a
`#number` or URL, or invoking the `create-ticket` skill for a freeform description), run the
architecture review, feature analysis and task breakdown, then **stop and present the plan
for approval before any implementation**.

Do not implement anything yourself in response to this command — delegate to the planner and
relay its plan and its approval request. Implementation proceeds only after the human
approves the plan.
