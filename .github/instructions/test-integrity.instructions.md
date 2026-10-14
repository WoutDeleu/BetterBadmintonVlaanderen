---
applyTo: "**/src/test/**,**/*.test.ts,**/*.test.tsx,**/*.spec.ts,**/*.spec.tsx,backend/pom.xml,frontend/package.json,frontend/tsconfig.json,.github/workflows/**"
---
Test or quality-gate change. Compare with the base branch and report, with file:line:
- assertions removed or weakened, expected values changed, tolerances widened
- `@Disabled`, `skip`, `xit`, `.only`, or excluded tests and packages
- test discovery narrowed, coverage or lint or compiler settings loosened, warnings suppressed
- new mocks that replace the behavior under test; altered fixtures that hide a case
- ArchUnit rules removed or given exclusions

For each behavior-changing test edit, ask which approved requirement justifies it. Check the new test would fail for a plausible wrong implementation. Legitimate changes are fine; report what the diff shows.
