package uz.demo.keyboardhero.ui.component.cover

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import uz.demo.keyboardhero.ui.theme.KColor

@Composable
fun KBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Surface(
        color = KColor.surface,
        modifier = modifier.fillMaxSize(),
    ) {
        content()
    }
}
