# ParkingMobile (Jetpack Compose + Material 3)

Jetpack Compose implementation of the requested parking app flow using Material 3 and dynamic light/dark theme support.

## Screen Sequence (0 → 8)

0. Splash Screen  
1. Slider - Hassle-Free Parking  
2. Slider - Charging Solution  
3. Slider - Auto Payments  
4. Login  
5. Dashboard  
6. Add Option (vehicle options + add car)  
7. Payment  
8. Charging

## Theme

- Material 3 UI
- Dynamic color on Android 12+
- Fallback custom light/dark palettes with neon-green accent

## Build

```bash
gradle :app:assembleDebug
```

## CI/CD

Workflow: `.github/workflows/android-ci-cd.yml`
- Push/PR: lint + debug assemble + artifact upload
- Tags (`v*`): release assemble + artifact upload
