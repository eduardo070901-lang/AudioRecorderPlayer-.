# Implementation Plan - Upgrade SDK and Fix AGP Deprecations

The user wants to resolve several build configuration warnings and update the project to target the latest Android SDK (API 37).

## Proposed Changes

### [Gradle Configuration]

#### [MODIFY] [gradle.properties](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/gradle.properties)
- Change `android.newDsl=false` to `android.newDsl=true` to align with AGP 9.0+ defaults and resolve the `BaseAppModuleExtension` deprecation warning.

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/app/build.gradle.kts)
- Update `compileSdk` from 35 to 37.
- Update `targetSdk` from 35 to 37 in the `defaultConfig` block.

## Verification Plan

### Automated Tests
- Run `gradle_sync` to ensure the project structure is updated correctly with the new DSL and SDK.
- Run `gradle_build` (specifically `app:assembleDebug`) to verify that the project still compiles and the warnings are resolved.

### Manual Verification
- Check the build logs to confirm that the deprecation warning for `BaseAppModuleExtension` and the SDK version warnings are gone.
