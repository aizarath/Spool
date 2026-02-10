package com.aizarath.spool.ui.theme

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



private val DarkColorScheme = darkColorScheme(
    primary = DarkGreen,
    secondary = MossGreen,
    tertiary = MidnightGreen,

    surface = Phantom,

    error = CherryWine,
    onError = Beige,

    outline = Beige,
)

private val LightColorScheme = lightColorScheme(
    primary = Beige,
    onPrimary = Phantom,
    secondary = Beige,
    onSecondary = MidnightGreen,
    tertiary = EggWash,
    onTertiary = DarkGreen,

    background = Beige,
    onBackground = Phantom,
    surface = Tangerine,
    onSurface = Phantom,
    onSurfaceVariant = Slate,

    error = Maple,
    onError = Phantom,

    outline = Phantom,
    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun SpoolTheme(
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}