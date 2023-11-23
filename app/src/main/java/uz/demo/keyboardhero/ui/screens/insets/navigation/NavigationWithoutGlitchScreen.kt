@file:OptIn(ExperimentalFoundationApi::class)

package uz.demo.keyboardhero.ui.screens.insets.navigation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.component.buttons.KButton
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.component.input.KTextField
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme

@Composable
fun NavigationWithoutGlitchRoute(
    openCenteredScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationWithoutGlitchScreen(
        modifier = modifier,
        openCenteredScreen = openCenteredScreen,
    )
}

@Composable
fun NavigationWithoutGlitchScreen(
    modifier: Modifier = Modifier,
    openCenteredScreen: () -> Unit = {},
) {
    val focusRequester = remember {
        FocusRequester()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.surface)
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .padding(16.dp),
    ) {
        KTextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            value = "",
            hint = stringResource(R.string.awesome_hint),
            onValueChange = {},
        )

        Spacer(modifier = Modifier.weight(1f))

        KButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(R.string.open_next_screen),
            onClick = openCenteredScreen,
        )
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun PreviewNavigationWithoutGlitch() {
    KeyboardHeroTheme {
        KBackground {
            NavigationWithoutGlitchScreen()
        }
    }
}
