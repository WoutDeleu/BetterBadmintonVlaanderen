---
applyTo: "mobile/**"
---
Planned React Native (Expo) app; `mobile/` does not exist yet. Apply the frontend rules, plus:
- Behavior must match the web frontend and the backend contract for the same feature. Flag differences.
- Keep platform-specific code (navigation, storage, device APIs) in adapters, away from business rules.
- Shared types and the API client should be reused, not copied.
