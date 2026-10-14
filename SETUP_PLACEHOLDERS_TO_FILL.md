# Setup placeholders to fill in

This file centralizes the placeholder values currently found in `README.md` and `DOCUMENTATION.md`. Fill in the blanks here, then copy the values into the corresponding snippets in those files.

## 1) Local development prerequisites (from `README.md`)

Fill in the versions/commands you use locally.

- Java version (used by backend): `25`
  - (Expected: Java 25+)
- Node.js version (used by frontend): `22`
  - (Expected: Node 22+)
- Docker version / Docker Compose version: `Docker + Docker Compose (any recent)`

### Commands

Pick a command set and paste it into the `TODO` blocks in `README.md`.

- Everything (backend + frontend + database):
  - `TODO`
- Backend only:
  - `TODO`
- Frontend only:
  - `TODO`
- Mobile app (Expo / React Native):
  - `TODO`

### Tests

- Unit/integration test instructions/commands:
  - `__________`

### License

- License name or link to the file used by this repo (e.g. MIT / Apache-2.0):
  - `__________`

## 2) External links to toernooi.nl (from `DOCUMENTATION.md`)

The backend builds external URLs using a configurable base URL + path fragments.

### YAML snippet fields

In `DOCUMENTATION.md`, this section contains `__________` blanks that you should fill in:

```yaml
external-links:
  toernooi-nl:
    base-url: <to be verified>
    player-path: <to be verified>
    tournament-path: <to be verified>
```

Fill in:

- `external-links.toernooi-nl.base-url`
  - Value: `__________`
  - Explanation: Scheme + host + (optional) common prefix for Badminton Vlaanderen’s toernooi.nl environment.

- `external-links.toernooi-nl.player-path`
  - Value: `__________`
  - Explanation: Path template or path fragment that points to a *player profile*.
  - Include the place where you substitute the imported player id, e.g. `{playerId}` or a similar placeholder used by your app.

- `external-links.toernooi-nl.tournament-path`
  - Value: `__________`
  - Explanation: Path template or path fragment that points to a *tournament page*.
  - Include the place where you substitute the imported tournament id, e.g. `{tournamentId}` or a similar placeholder.

### URL concatenation guidance

- How to combine them (what the app expects):
  - `base-url + player-path + <playerId substitution>`
  - `base-url + tournament-path + <tournamentId substitution>`

- IDs substitution method (path segment vs query param): `__________`

- Source of truth / verification example URL(s):
  - Player example URL (working in a browser): `__________`
  - Tournament example URL (working in a browser): `__________`

## 3) Secrets / environment configuration placeholders (from `DOCUMENTATION.md`)

`DOCUMENTATION.md` contains an illustrative Kubernetes Secret example with placeholder base64 values.

### Kubernetes Secret (base64)

Fill in what you want to store in the secret (you will still base64-encode the raw values):

- Raw database password (NOT base64): `__________`
  - Then base64-encode it for the manifest.

- Raw JWT signing secret (NOT base64): `__________`
  - Then base64-encode it for the manifest.

- Optional: command(s you used to generate base64:
  - `__________`

(Keep actual secrets out of git; prefer setting them via your local environment/secret manager.)

## 4) Cross-check list

After filling the blanks above, verify that the values you chose resolve to working URLs (for the toernooi links) and that your local test/setup can run with the chosen versions and commands.
