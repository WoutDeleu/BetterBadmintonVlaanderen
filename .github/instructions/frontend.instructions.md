---
applyTo: "frontend/src/**"
---
React + TypeScript. Keep presentation (components), application behavior (hooks, state) and API access (services) separate.
- Business rules do not live in components or hooks. Do not duplicate rules the backend owns unless the change says why.
- TypeScript: no `any`, no casts that bypass an invariant. Branded types alone do not validate; domain values need runtime validation at the boundary where they enter.
- Check that API types match the backend contract, and that loading, error and empty states are handled.
- Flag new `eslint-disable`, `@ts-ignore` or `@ts-expect-error` without a reason.
