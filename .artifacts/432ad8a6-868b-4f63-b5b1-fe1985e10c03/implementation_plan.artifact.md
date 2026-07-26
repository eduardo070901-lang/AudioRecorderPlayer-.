# Fix Render Issue in Layout Preview

The Layout Preview is currently reporting a fidelity warning because the project is using `compileSdk 37`, while the layout editor's renderer only supports up to API 36. This causes the preview to be inaccurate or potentially crash.

## User Review Required

> [!IMPORTANT]
> I am lowering the `compileSdk` and `targetSdk` from 37 to 36. API 37 (Baklava) is still in early stages and not yet fully supported by all IDE tools. API 36 (Vanilla Ice Cream) is the current stable maximum for the renderer.

## Proposed Changes

### Build Configuration

#### [MODIFY] [app/build.gradle.kts](file:///C:/Users/pc/AndroidStudioProjects/MiSegundaApp/app/build.gradle.kts)
- Lower `compileSdk` to 36.
- Lower `targetSdk` to 36.

## Verification Plan

### Automated Tests
- Run `gradle sync` to ensure the project still builds correctly with API 36.
- Verify that the layout preview no longer shows the fidelity warning.

### Manual Verification
- Open `activity_main.xml` in the Layout Editor and check if the warning is gone.
