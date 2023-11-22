package uz.demo.keyboardhero.ui.screens.insets.centered

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme
import uz.demo.keyboardhero.utils.insets.Screen

@Composable
fun CenteredAdjustNothingRoute(
    modifier: Modifier = Modifier,
) {
    CenteredAdjustNothingScreen(
        modifier = modifier,
    )
}

@Composable
fun CenteredAdjustNothingScreen(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(KColor.background)
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.Screen),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .background(KColor.primary, RoundedCornerShape(16.dp))
                .padding(24.dp),
        ) {
            Text(
                text = stringResource(R.string.glitch_note),
                color = KColor.onPrimary,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewCenteredAdjustNothing() {
    KeyboardHeroTheme {
        KBackground {
            CenteredAdjustNothingScreen()
        }
    }
}
