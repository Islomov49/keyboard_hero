package uz.demo.keyboardhero.utils.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Brush
import uz.demo.keyboardhero.ui.theme.KColor

val primaryContainerBrush
    @Composable @ReadOnlyComposable
    get() = Brush.verticalGradient(
        colors = listOf(
            KColor.onSurface,
            KColor.onSurfaceVariant,
        ),
    )
