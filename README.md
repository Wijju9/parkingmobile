# ParkingMobile (Jetpack Compose + Material 3)

A Jetpack Compose Android UI implementation inspired by the provided parking/charging mobile design.

## Implemented screens

- Charging status
- Parking onboarding
- Dashboard with station status
- Auto payments onboarding
- Brand splash-style screen
- Payment summary
- Charging solution onboarding
- Vehicle selector

The app uses Material 3 with dynamic color (Android 12+) and light/dark fallback color schemes.

## Run locally

```bash
gradle :app:assembleDebug
```

## CI/CD

GitHub Actions workflow at `.github/workflows/android-ci-cd.yml` runs lint + debug build on push/PR and creates release artifacts on version tags (`v*`).
