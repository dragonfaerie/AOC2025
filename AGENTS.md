# Repository Guidelines

## Project Structure & Module Organization
Kotlin sources live under `src`, with one package per puzzle day (`day01`, `day02`, etc.) and matching files such as `day1asolve.kt`. `Main.kt` is the entrypoint; toggle the desired `dayXXpartY()` invocations inside `main()` when working on a new puzzle. Puzzle inputs sit beside their solver in each `dayXX` folder, while reusable sample data belongs under `src/exampledata`. Keep non-source artifacts (notes, diagrams) outside `src` to avoid shipping them.

## Build, Test, and Development Commands
- `kotlinc src -include-runtime -d build/aoc2025.jar` — compiles every `.kt` file into a runnable jar; rerun whenever you add a new day or adjust shared helpers.
- `java -jar build/aoc2025.jar` — executes the compiled jar and runs whatever functions are invoked from `main()`.
- IntelliJ IDEA: open `aoc2025.iml`, mark `src` as Source Root, and use the built-in Run Configuration targeting `Main.kt` for quick iteration.

## Coding Style & Naming Conventions
Follow Kotlin official style: 4-space indentation, braces on the same line, `camelCase` identifiers, and upper camel case for classes/objects if they appear later. Keep per-day helpers private to the package when possible, and group parsing utilities at the top of each file. Name functions `dayNNpart[a|b]` so the dispatcher in `Main.kt` stays predictable. Keep inputs as lowercase filenames without extensions (mirroring existing `day1input`) to simplify file reading logic.

## Testing Guidelines
There is no formal test harness yet; use example data under `src/exampledata` or the puzzle-provided samples stored alongside each solver. Prefer writing lightweight verification helpers (e.g., `check(day6parta() == 1234)`) at the bottom of a file and wrapping them in `if (DEBUG)` blocks so they can be toggled off in `Main.kt`. When introducing automated tests, stick with `kotlin.test` or JUnit, name files `DayNNTest.kt`, and place them under a future `test` root mirroring the `dayNN` packages.

## Commit & Pull Request Guidelines
History shows concise, lowercase subject lines (`day 8 and 24`, `day 7 complete`). Keep this format: summarize scope in ≤60 characters, no trailing punctuation. For pull requests, include the puzzle day, mention affected inputs, enumerate runnable commands (`java -jar …`), and add screenshots or text output if the change affects console formatting. Link the relevant AoC problem statement if context is required, and ensure every PR states how the result was validated.
