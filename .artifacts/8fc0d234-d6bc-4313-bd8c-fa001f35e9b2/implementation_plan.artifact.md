# Fix Unresolved Reference 'kotlinOptions' in build.gradle.kts

The project is currently a Java-based Android project, but the `app/build.gradle.kts` file contains a `kotlinOptions` block. This block requires the Kotlin Gradle plugin to be applied, which is currently missing from the project configuration. Since there is no Kotlin source code in the project, the `kotlinOptions` block is unnecessary.

## User Review Required

> [!NOTE]
> The project currently only contains Java source files (`MainActivity.java`). I will remove the Kotlin-specific configuration to resolve the sync error. If you intended to use Kotlin in this project, please let me know, and I can add the necessary Kotlin plugins instead.

## Proposed Changes

### app

#### [MODIFY] [build.gradle.kts](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/app/build.gradle.kts)
- Remove the `kotlinOptions` block (lines 35-37) to fix the "Unresolved reference" error.

## Verification Plan

### Automated Tests
- Run Gradle sync to verify that the "Unresolved reference 'kotlinOptions'" error is resolved.
- Run `./gradlew :app:assembleDebug` to ensure the project builds successfully.

### Manual Verification
- Verify in Android Studio that the `build.gradle.kts` file no longer shows syntax errors.
