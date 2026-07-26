# Implementation Plan - Fix Layout Fidelity Warning

The project is currently targeting Android API 37, but the Android Studio Layout Editor's rendering engine only supports up to API 36. This causes a "Layout fidelity warning" in the preview, indicating that the graphics might not be accurate or could crash.

## User Review Required

> [!IMPORTANT]
> The project currently uses XML layouts (`activity_main.xml`) and Java, despite the system prompt mentioning Jetpack Compose. I will address the layout rendering issue in the existing XML-based setup.

## Proposed Changes

### Build Configuration

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/app/build.gradle.kts)
- Downgrade `compileSdk` from `37` to `36`.
- Downgrade `targetSdk` from `37` to `36`.
- Ensure consistency with `buildToolsVersion` which is already at `36.0.0`.

## Verification Plan

### Automated Tests
- Run `gradle_sync` to ensure the project structure is updated.
- Verify that the project builds successfully.

### Manual Verification
- The user can verify in the Android Studio Layout Editor that the "Layout fidelity warning" for API 37 has disappeared and the preview renders correctly using API 36.
