package kr.hs.pandabear.recom.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = RecomColor.Main,
    secondary = RecomColor.Main500,
    background = RecomColor.BackgroundColor,
    surface = RecomColor.White,
    error = Color.Red,
    onPrimary = RecomColor.White,
    onSecondary = RecomColor.White,
    onBackground = RecomColor.Black,
    onSurface = RecomColor.White,
    onError = Color.Red
)

private val DarkColorPalette = darkColors(
    primary = RecomColor.Main,
    secondary = RecomColor.Main700,
    background = RecomColor.DarkBackgroundColor,
    surface = RecomColor.White,
    error = Color.Red,
    onPrimary = RecomColor.White,
    onSecondary = RecomColor.White,
    onBackground = RecomColor.White,
    onSurface = Color.White,
    onError = Color.Red
)

@Composable
fun RecomTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}