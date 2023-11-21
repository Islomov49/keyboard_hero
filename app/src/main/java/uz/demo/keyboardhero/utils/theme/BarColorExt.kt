package uz.demo.keyboardhero.utils.theme

import android.graphics.Color
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext

@Composable
fun enableEdgeToEdge(isDarkMode: Boolean = isSystemInDarkTheme()) {
    val activity = LocalContext as ComponentActivity
    DisposableEffect(isDarkMode) {
        activity.enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.auto(
                Color.TRANSPARENT,
                Color.TRANSPARENT,
            ) { isDarkMode },
            navigationBarStyle = SystemBarStyle.auto(
                lightScrim,
                darkScrim,
            ) { isDarkMode },
        )

        onDispose {}
    }
}
