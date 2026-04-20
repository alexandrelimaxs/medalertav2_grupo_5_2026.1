# OpenCode Agent Instructions

## Repository State & Migration Notice
- **CRITICAL**: The `README.md` describes the *target* state of the repository (Maven, Java 17, `src/main/java` structure), but the repository is currently transitioning from a legacy state. 
- Always verify if `pom.xml` exists. If it does not, you are working in the legacy codebase with root directories `backend/`, `frontend/`, and `inicio/`. Do not run `mvn` commands until the migration to a standard Maven structure is complete.

## Project Scope & Activities
- **CRITICAL**: Always refer to the `TRABALHO.md` file as the primary source of truth for the activities, deliverables, and requirements that must be accomplished throughout this project. It contains the detailed scope for tests, code inspection, and deadlines.

## Architecture & Entrypoint
- This is a legacy Java Swing OOP application being refactored for a Software Quality and Testing class.
- The main entrypoint of the application is `inicio.MedAlerta` located at `inicio/MedAlerta.java`.
- The GUI is built using Java Swing (found in the `frontend/` package).

## Testing Conventions (Target)
- Once the Maven environment is set up, tests should be written in `src/test/java`.
- **Unit Tests**: Use JUnit (4/5) and Mockito for isolated business logic (`backend`).
- **Integration Tests**: Place them under `backend/integracao` for flows between modules and file persistence.
- **GUI / System Tests**: Use `AssertJ Swing` for the Swing components (`frontend`).
- **Code Quality & Mutation Testing**: The project uses JaCoCo for coverage and PIT (Pitest) for mutation testing. Always ensure new tests are robust enough to pass mutation coverage.

## Modifying Code
- Respect the existing Java Swing patterns. Do not introduce non-Swing UI libraries.
- When writing tests, focus on identifying technical debt and refactoring legacy logic to be more testable.

## Git & GitHub Workflow
- **Pull Requests**: Always open PRs against the fork repository (`alexandrelimaxs/medalertav2_grupo_5_2026.1`), **NOT** the original upstream repository. If using `gh pr create`, explicitly specify the repo with `--repo alexandrelimaxs/medalertav2_grupo_5_2026.1`.
