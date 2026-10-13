# Better Badminton Vlaanderen

Companion app for the Badminton Vlaanderen portal. See `README.md` for what it is and how to run it.

## Documentation rules

Keep docs short and true. Less is better; stale docs are worse than none.

- `README.md`: what it is, how to run it. Nothing else. Do not copy feature detail into it.
- `DOCUMENTATION.md`: the single place for feature specs, architecture and API. Planned features are marked as such.
- Do not create new doc files unless asked. Do not add docs for things the code already shows.
- Tickets and requirements live in the GitHub Project (https://github.com/users/WoutDeleu/projects/9), not in the repo.

When a change affects any of these, update the doc in the same change:
- how to run, build or test the app -> `README.md`
- a feature's behavior, an API endpoint or the architecture -> the matching section in `DOCUMENTATION.md`
- a new or removed feature -> the feature line in `README.md` and its section in `DOCUMENTATION.md`

Before finishing a task, check that the run instructions in `README.md` still work and that nothing you changed contradicts `DOCUMENTATION.md`. Remove text that is no longer true instead of adding a note next to it.

## Architecture

Backend uses hexagonal architecture (`domain`, `application`, `infrastructure`), enforced by the ArchUnit tests in `backend/src/test/.../architecture`. Do not break them.
