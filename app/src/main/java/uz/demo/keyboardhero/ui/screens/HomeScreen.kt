package uz.demo.keyboardhero.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.component.buttons.KButton
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme

@Composable
fun HomeRoute(
    openWindowsInsets: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        modifier = modifier,
        openWindowsInsets = openWindowsInsets,
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUIState,
    modifier: Modifier = Modifier,
    openWindowsInsets: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.surface)
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.windows_insets),
                onClick = openWindowsInsets,
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.windows_insets_advanced),
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.keyboard_types),
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.focus_requester),
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.bring_to_view),
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                KButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.average_form),
                    containerColor = KColor.errorContainer,
                    contentColor = KColor.onErrorContainer,
                )

                Spacer(modifier = Modifier.width(16.dp))

                KButton(
                    modifier = Modifier.weight(1f),
                    text = stringResource(R.string.stunning_form),
                    containerColor = KColor.secondaryContainer,
                    contentColor = KColor.onSecondaryContainer,
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHome() {
    KeyboardHeroTheme {
        HomeScreen(
            uiState = HomeUIState(),
        )
    }
}
