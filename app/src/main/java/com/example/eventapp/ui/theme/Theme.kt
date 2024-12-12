// Theme.kt
package com.example.eventapp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = Purple40,
    primaryContainer = Purple80,
    secondary = PurpleGrey40
)

private val DarkColorPalette = darkColorScheme(
    primary = Purple80,
    primaryContainer = PurpleGrey80,
    secondary = PurpleGrey40
)

@Composable
fun EventAppTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography(),
        shapes = Shapes,
        content = content
    )
}