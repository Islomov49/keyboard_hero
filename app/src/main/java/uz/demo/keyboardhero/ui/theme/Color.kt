package uz.demo.keyboardhero.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val Grayscale0 = Color(0xFFFFFFFF)
private val Grayscale3 = Color(0xFFF7F7F8)
private val Grayscale5 = Color(0xFFF1F1F4)
private val Grayscale10 = Color(0xFFE3E2E6)
private val Grayscale20 = Color(0xFFD0CFD4)
private val Grayscale80 = Color(0xFF34353D)
private val Grayscale85 = Color(0xFF2A2B34)
private val Grayscale90 = Color(0xFF1F2028)
private val Grayscale95 = Color(0xFF151519)
private val Grayscale97 = Color(0xFF0F1011)
private val Grayscale100 = Color(0xFF000000)

private val Black60 = Color(0x990F1011)
private val Black10 = Color(0x1A0F1011)
private val Black5 = Color(0x0D0F1011)
private val Black20 = Color(0x330F1011)

private val White60 = Color(0x99DCE6F0)
private val White10 = Color(0x1ADCE6F0)
private val White20 = Color(0x33DCE6F0)
private val White5 = Color(0x0DDCE6F0)

private val CyanBlue = Color(0xFF3DC1CA)

val KLightColorScheme = lightColorScheme(
    primary = Color(0xFF07BD9C),
    onPrimary = Grayscale0,
    primaryContainer = Color(0xFFE6F8F5),
    onPrimaryContainer = Color(0xFF01976E),
    secondary = Color(0xFF1B98FF),
    onSecondary = Grayscale0,
    secondaryContainer = Color(0xFFE8F5FF),
    onSecondaryContainer = Color(0xFF086AFF),
    tertiary = Color(0xFFFF8311),
    onTertiary = Grayscale0,
    tertiaryContainer = Color(0xFFFFF3E7),
    onTertiaryContainer = Color(0xFFFC8C25),
    error = Color(0xFFFA1E25),
    onError = Grayscale0,
    errorContainer = Color(0xFFFFE9E9),
    onErrorContainer = Color(0xFFFA1E25),
    surfaceDim = Grayscale5,
    background = Grayscale0,
    surface = Grayscale0,
    surfaceBright = Grayscale0,
    surfaceContainerLowest = Grayscale0,
    surfaceContainerLow = Grayscale3,
    surfaceContainer = Grayscale5,
    surfaceContainerHigh = Grayscale10,
    surfaceContainerHighest = Grayscale20,
    onSurface = Grayscale97,
    onSurfaceVariant = Black60,
    outline = Black20,
    outlineVariant = Black5,
)

val KDarkColorScheme = darkColorScheme(
    primary = Color(0xFF33F8D4),
    onPrimary = Color(0xFF076857),
    primaryContainer = Color(0xFF07BD9C),
    onPrimaryContainer = Color(0xFFE6F8F5),
    secondary = Color(0xFF8DCCFF),
    onSecondary = Color(0xFF086AFF),
    secondaryContainer = Color(0xFF1B98FF),
    onSecondaryContainer = Color(0xFFE8F5FF),
    tertiary = Color(0xFFFFC289),
    onTertiary = Color(0xFFC25D00),
    tertiaryContainer = Color(0xFFFF8311),
    onTertiaryContainer = Color(0xFFFFF3E7),
    error = Color(0xFFFFAFB1),
    onError = Color(0xFFFA1E25),
    errorContainer = Color(0xFFFA1E25),
    onErrorContainer = Color(0xFFFFE9E9),
    background = Grayscale97,
    surfaceDim = Grayscale97,
    surface = Grayscale97,
    surfaceBright = Grayscale80,
    surfaceContainerLowest = Grayscale100,
    surfaceContainerLow = Grayscale95,
    surfaceContainer = Grayscale90,
    surfaceContainerHigh = Grayscale85,
    surfaceContainerHighest = Grayscale80,
    onSurface = Grayscale10,
    onSurfaceVariant = White60,
    outline = White20,
    outlineVariant = White5,
)
