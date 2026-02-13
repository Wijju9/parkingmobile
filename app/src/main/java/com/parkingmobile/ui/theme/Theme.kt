package com.parkingmobile.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val LightScheme = lightColorScheme(
    primary = Color(0xFFB3FF2D),
    onPrimary = Color(0xFF0F1115),
    secondary = Color(0xFF1B2232),
    surface = Color(0xFFF8F9F7),
    surfaceContainerLow = Color(0xFFF0F1EE)
)

private val DarkScheme = darkColorScheme(
    primary = Color(0xFFB3FF2D),
    onPrimary = Color(0xFF10150A),
    secondary = Color(0xFFCDD4E5),
    surface = Color(0xFF0E1118),
    surfaceContainerLow = Color(0xFF171C26)
)

@Composable
fun ParkingMobileTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkScheme
        else -> LightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
