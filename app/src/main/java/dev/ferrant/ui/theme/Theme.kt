package dev.ferrant.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat


private val LightColorScheme = lightColorScheme(
    primary = LightPurple,
    onPrimary = Light_White,
    primaryContainer = LightPurpleLight,
    onPrimaryContainer = LightPurpleDark,
    secondary = LightGray,
    onSecondary = Light_White,
    secondaryContainer = LightGrayLight,
    onSecondaryContainer = LightGrayDark,
    tertiary = LightGreen,
    onTertiary = Light_White,
    tertiaryContainer = LightGreenLight,
    onTertiaryContainer = LightGreenDark,
    error = LightRed,
    errorContainer = LightRedLight,
    onError = Light_White,
    onErrorContainer = LightRedDark,
    background = LightGrayLighter,
    onBackground = LightGrayDarkest,
    surface = LightGrayLighter,
    onSurface = LightGrayDarkest,
    surfaceTint = LightGreen,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkPurpleLight,
    onPrimary = DarkPurpleDark,
    primaryContainer = DarkPurple,
    onPrimaryContainer = DarkPurpleLighter,
    secondary = DarkGray,
    onSecondary = DarkGrayDark,
    secondaryContainer = DarkGrayDarker,
    onSecondaryContainer = DarkGrayLight,
    tertiary = DarkGreenLight,
    onTertiary = DarkGreenDark,
    tertiaryContainer = DarkGreen,
    onTertiaryContainer = DarkGreenLighter,
    error = DarkRedLight,
    errorContainer = DarkRed,
    onError = DarkRedDarker,
    onErrorContainer = DarkRedLighter,
    background = DarkGrayDarkest,
    onBackground = DarkGrayLighter,
    surface = DarkGrayDarkest,
    onSurface = DarkGrayLighter,
    surfaceTint = DarkGreenLight,
)

@Composable
fun CryptoMarketTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.primary.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}