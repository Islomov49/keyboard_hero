package uz.demo.keyboardhero.utils.insets

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.runtime.Composable
import androidx.compose.runtime.NonRestartableComposable

val WindowInsets.Companion.Screen: WindowInsets
    @Composable
    @NonRestartableComposable
    get() = WindowInsets.systemBars.union(WindowInsets.displayCutout)
