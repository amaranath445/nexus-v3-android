# NEXUS V3.1 Android Project

WebView-based Android shell for the NEXUS V3.1 local-first assistant.

## Features

- **V3.1 HTML Asset** — Embedded local-first assistant interface
- **Android WebView** — Full-screen web experience
- **JavaScript-to-Android Bridge** — Seamless JS↔Android communication
- **Vibration Support** — Haptic feedback via Android vibration API
- **Notifications** — System notifications capability
- **Online Status Detection** — Network awareness
- **Fullscreen UI** — Material Design dark theme with no action bar
- **Portrait Orientation** — Locked to portrait mode

## Architecture

```
nexus-v3-android/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/nexus/v3/
│   │   │   │   └── MainActivity.java
│   │   │   ├── res/
│   │   │   │   ├── values/styles.xml
│   │   │   │   └── layout/activity_main.xml
│   │   │   ├── assets/
│   │   │   │   └── index.html
│   │   │   └── AndroidManifest.xml
│   │   └── test/
│   └── build.gradle
├── build.gradle
├── settings.gradle
└── README.md
```

## Permissions

- `VIBRATE` — Haptic feedback
- `POST_NOTIFICATIONS` — System notifications

## Theme

- **Parent**: Material.NoActionBar (fullscreen)
- **Font**: sans-serif
- **Status Bar**: `#050914` (dark)
- **Navigation Bar**: `#050914` (dark)

## Building

Requirements:
- Android Studio or Gradle
- Android SDK (API 26+)
- Java 11+

```bash
# Build APK
./gradlew build

# Build and run on device/emulator
./gradlew installDebug
```

## APK Generation

The project is APK-ready in structure. To generate a signed APK:

1. In Android Studio: `Build → Generate Signed Bundle / APK`
2. Configure keystore and signing details
3. Select release build type
4. APK will be generated in `app/release/`

## Development

### JavaScript Bridge

Communicate between JavaScript (in WebView) and Android:

```javascript
// From JavaScript
Android.vibrate(100);
Android.postNotification('Title', 'Message');
Android.getOnlineStatus();
```

### WebView Settings

Configure in `MainActivity.java`:
- JavaScript enabled
- DOM storage enabled
- Zoom controls hidden
- Mixed content policy

## License

MIT
