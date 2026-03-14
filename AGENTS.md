# AGENTS.md

## Project Overview

**Dark Purple Islands** is a JetBrains IntelliJ IDEA UI theme plugin. It provides a dark purple color scheme with Islands UX design, targeting IntelliJ 2024.1+. It is a pure resource/configuration project — there is no application code (no Kotlin/Java source files).

- **Author:** Nikola Chetelyazov
- **Plugin ID:** `com.github.avenue9977.darkpurpleislands`
- **License:** MIT
- **Based on:** The original [Dark Purple Theme](https://plugins.jetbrains.com/plugin/12100-dark-purple-theme) by Olga Berdnikova, redesigned for the Islands interface.

## Tech Stack

- **Build system:** Gradle 9.0 with Kotlin DSL (`build.gradle.kts`)
- **Gradle plugin:** `org.jetbrains.intellij.platform` v2.2.1
- **Target platform:** IntelliJ IDEA Community 2024.3
- **Compatibility:** `since-build` 252.25557 (defined in `plugin.xml`); build.gradle.kts sets `sinceBuild = "241"` with no upper bound
- **Parent theme:** Islands Dark
- **Parent color scheme:** Darcula

## Project Structure

```
dark-purple-islands/
├── build.gradle.kts              # Build config, dependencies, source set override
├── settings.gradle.kts           # Root project name
├── gradle.properties             # JVM args, parallel build
├── gradlew / gradlew.bat         # Gradle wrapper scripts
├── gradle/wrapper/               # Gradle wrapper JAR + properties
├── LICENSE                       # MIT
├── resources/
│   ├── META-INF/
│   │   ├── plugin.xml            # Plugin descriptor (id, name, vendor, version, description, extension point)
│   │   └── pluginIcon.svg        # Marketplace / IDE icon
│   └── theme/
│       ├── darkpurpleislands.theme.json   # UI theme definition (colors, component styles, Islands config, icon palette)
│       └── darkPurpleScheme.xml           # Editor color scheme (syntax highlighting, gutter, diff, console colors)
└── build/                        # Build output (gitignored)
```

There is **no `src/` directory**. The `sourceSets.main.resources` is pointed at `resources/` in the project root.

## Key Files

### `resources/META-INF/plugin.xml`
Plugin manifest. Declares the plugin ID, version, vendor info, compatibility range, dependency on `com.intellij.modules.platform`, and registers the theme via `<themeProvider>`.

### `resources/theme/darkpurpleislands.theme.json`
The main UI theme file. Contains:
- **`colors`** block — named color variables (prefixed `dp`) reused throughout
- **`ui`** block — component-level style overrides (buttons, tabs, tool windows, popups, editor chrome, Islands-specific settings like `Island.arc`, `Island.borderWidth`)
- **`icons.ColorPalette`** — icon tinting overrides

The theme sets `"parentTheme": "Islands Dark"` and `"dark": true`.

### `resources/theme/darkPurpleScheme.xml`
The editor color scheme (XML, `parent_scheme="Darcula"`). Defines:
- Gutter colors (line numbers, caret row, indent guides, VCS annotations)
- Syntax highlighting for default token types (keywords, strings, numbers, operators, types, functions, fields, constants, comments, annotations)
- HTML/XML/CSS-specific colors
- Diff viewer colors
- Console/terminal ANSI colors
- Search result highlighting, brace matching, TODO, folded text, hyperlinks

## Build & Run

```bash
# Build the plugin (produces a ZIP in build/distributions/)
./gradlew buildPlugin

# Run a sandboxed IDE instance with the theme installed
./gradlew runIde
```

## Development Guidelines

- All changes are in JSON/XML resource files — no compilation step for theme edits, but a `buildPlugin` is needed to package.
- Color variables defined in the `colors` block of the `.theme.json` should be reused by name rather than hardcoding hex values in the `ui` block.
- The editor scheme XML uses raw hex values (no variable indirection).
- When adding support for new UI components, check the [IntelliJ Theme Reference](https://plugins.jetbrains.com/docs/intellij/themes-getting-started.html) for available keys.
- Test changes by running `./gradlew runIde` to launch a sandboxed IDE.