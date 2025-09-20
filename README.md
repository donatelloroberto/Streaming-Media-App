## Build & Quick Test (CI)

A GitHub Actions workflow builds a debug APK on each push/PR and uploads it as an artifact.

To get the APK:
1. Go to **Actions → [latest run] → Artifacts → app-debug-apk** and download `app-debug-apk`.
2. Install on an Android TV/emulator using `adb install -r app-debug.apk`.

Local build (if you clone later):
```bash
./gradlew clean assembleDebug
./gradlew test

Commit that change.

# 3 — Trigger CI and verify
- If you committed to `main` the workflow starts automatically.  
- If you opened a PR, pushing the branch triggers the workflow for the PR.  
- Open **Actions** (top menu) → click the latest run → watch logs. Important steps to watch: Gradle assembleDebug and “Upload debug APK artifact”.

# 4 — Download the APK artifact from Actions
1. Actions → click run → scroll to bottom → **Artifacts** → download `app-debug-apk`.  
2. Save to your computer. Filename will be `app-debug-apk` (zip or single file). Rename to `app-debug.apk` if needed.

# 5 — Install APK on your Android TV (network or local)
If you have a PC with `adb`:

- Connect device:
  ```bash
  adb devices
  # If using network ADB: on Android TV Settings > Developer options > ADB over network,
  # then: adb connect <tv-ip>:5555
  adb install -r path/to/app-debug.apk
  adb logcat -c
  adb logcat | grep -i YOUR_APP_TAG
