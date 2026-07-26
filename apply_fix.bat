@echo off
cd /d "C:\Users\pc\AndroidStudioProjects\MiSegundaApp"
if exist "activity_main_fixed.xml" (
  copy /Y "activity_main_fixed.xml" "app\src\main\res\layout\activity_main.xml"
  echo COPIED_FIXED_LAYOUT
) else (
  echo MISSING_FIXED_FILE
  exit /b 2
)
echo Starting gradle build...
call gradlew.bat assembleDebug --no-daemon --console=plain
if %ERRORLEVEL% neq 0 (
  echo BUILD_FAILED
  exit /b %ERRORLEVEL%
)
if not exist .git (
  git init
  git config user.name "Local User"
  git config user.email "local@example.com"
)
git add app\src\main\res\layout\activity_main.xml
echo Fix: make MaterialButtons wrap_content and set minWidth>commitmsg.txt
echo.>>commitmsg.txt
echo Co-authored-by: Copilot <223556219+Copilot@users.noreply.github.com>>commitmsg.txt
git commit -F commitmsg.txt
del commitmsg.txt
echo COMMIT_DONE
pause
