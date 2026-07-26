# Fix Unresolved Reference 'kotlinOptions'

The project's `app/build.gradle.kts` contains a `kotlinOptions` block, but the Kotlin Android plugin is not applied to the module. This causes a sync error because the `kotlinOptions` extension is provided by the Kotlin plugin.

Although the current source code is in Java (`MainActivity.java`), the build script is configured to use Kotlin options. Applying the Kotlin plugin will resolve this error and enable Kotlin support for the project.

## Proposed Changes

### Gradle Configuration

#### [MODIFY] [libs.versions.toml](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/gradle/libs.versions.toml)
Add the Kotlin plugin definition to the `[versions]` and `[plugins]` sections.

#### [MODIFY] [build.gradle.kts (root)](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/build.gradle.kts)
Define the Kotlin Android plugin in the top-level `plugins` block.

#### [MODIFY] [build.gradle.kts (app)](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/app/build.gradle.kts)
Apply the Kotlin Android plugin in the `plugins` block.

## Verification Plan

### Automated Tests
- Run `gradlew sync` (or wait for IDE sync) to verify the "Unresolved reference" error is gone.
- Run `gradlew :app:assembleDebug` to ensure the project builds successfully.
