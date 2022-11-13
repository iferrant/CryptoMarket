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
    primary = LightGreen,
    onPrimary = Light_White,
    primaryContainer = LightGreenLight,
    onPrimaryContainer = LightGreenDark,
    secondary = LightGray,
    onSecondary = Light_White,
    secondaryContainer = LightGrayLight,
    onSecondaryContainer = LightGrayDark,
    tertiary = LightPurple,
    onTertiary = Light_White,
    tertiaryContainer = LightPurpleLight,
    onTertiaryContainer = LightPurpleDark,
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
    primary = DarkGreenLight,
    onPrimary = DarkGreenDark,
    primaryContainer = DarkGreen,
    onPrimaryContainer = DarkGreenLighter,
    secondary = DarkGray,
    onSecondary = DarkGrayDark,
    secondaryContainer = DarkGrayDarker,
    onSecondaryContainer = DarkGrayLight,
    tertiary = DarkPurpleLight,
    onTertiary = DarkPurpleDark,
    tertiaryContainer = DarkPurple,
    onTertiaryContainer = DarkPurpleLighter,
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