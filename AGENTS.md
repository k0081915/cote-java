# Repository Guidelines

## Project Structure & Module Organization
This repository is a lightweight Java coding-test workspace.

- `src/problems/`: problem solutions, organized by platform and problem id.
- `src/problems/boj/p13398/Main.java`: current BOJ solution entry point.
- `src/solved_log.md`: structured log for solved problems and retrospectives.
- `out/`: compiled class output (generated; do not commit build artifacts).
- `.idea/`: IDE metadata.

When adding new solutions, follow the existing package pattern:
`src/problems/<platform>/<problemId>/Main.java` with matching `package` names.

## Build, Test, and Development Commands
No Gradle/Maven wrapper is configured; use `javac`/`java` directly.

- Compile one solution:
  - `javac -d out src/problems/boj/p13398/Main.java`
- Run compiled class:
  - `java -cp out problems.boj.p13398.Main`
- Run with input file (PowerShell):
  - `Get-Content .\input.txt | java -cp out problems.boj.p13398.Main`

If multiple problems are added, compile all sources with:
`javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })`

## Coding Style & Naming Conventions
- Language: Java (standard library only unless explicitly needed).
- Indentation: 4 spaces; keep methods and blocks consistently formatted.
- Class naming: `Main` for BOJ-style submissions.
- Package naming: lowercase dot-separated path matching folders (e.g., `problems.boj.p13398`).
- Prefer fast I/O (`BufferedReader`, `StringTokenizer`) for algorithm problems.

## Testing Guidelines
Automated test framework is not set up yet. Validate using sample/custom input cases.

- Keep problem-specific sample inputs in local files such as `input.txt`.
- Re-run after each logic change and verify expected stdout exactly.
- Add edge-case checks (N=1, min/max values, overflow-sensitive cases).

## Commit & Pull Request Guidelines
Current history has a single initial commit (`init`), so conventions are not yet strict.

- Use short, imperative commit messages, optionally scoped:
  - `boj-13398: implement DP transition`
  - `docs: update solved log template`
- PRs should include:
  - What problem/module changed
  - Why the approach was chosen (briefly)
  - How it was validated (input cases used, expected vs actual)
