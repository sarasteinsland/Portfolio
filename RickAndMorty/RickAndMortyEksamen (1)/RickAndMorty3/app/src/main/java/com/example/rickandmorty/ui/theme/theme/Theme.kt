package com.example.rickandmorty.ui.theme.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme

// Define custom dark and light color schemes
val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF00FFAB),
    secondary = Color(0xFF1E90FF),
    background = Color(0xFF121212),
    surface = Color(0xFF1C1C1C),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color(0xFFDCDCDC),
    onSurface = Color(0xFFDCDCDC)
)

val LightColorScheme = lightColorScheme(
    primary = Color(0xFF00FFAB),
    secondary = Color(0xFF1E90FF),
    background = Color(0xFFFFFFFF),  // Light color for light theme background
    surface = Color(0xFFF0F0F0),
    onPrimary = Color.Black,
    onSecondary = Color.Black,
    onBackground = Color(0xFF333333), // Darker text on light background
    onSurface = Color(0xFF333333)
)

// Define custom shapes
val CustomShapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(16.dp)
)

@Composable
fun RickAndMortyTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
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
        shapes = CustomShapes,
        content = content
    )
}
