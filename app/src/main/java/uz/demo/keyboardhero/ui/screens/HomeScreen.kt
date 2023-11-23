package uz.demo.keyboardhero.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
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
    openWindowsInsetsFirstExample: () -> Unit,
    openWindowsInsetsSecondExample: () -> Unit,
    openWindowsInsetsThirdExample: () -> Unit,
    openWindowsInsetsFourthExample: () -> Unit,
    openFocusFormExample: () -> Unit,
    openBringToViewExample: () -> Unit,
    openBonusLayoutExample: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        modifier = modifier,
        openWindowsInsetsFirstExample = openWindowsInsetsFirstExample,
        openWindowsInsetsSecondExample = openWindowsInsetsSecondExample,
        openWindowsInsetsThirdExample = openWindowsInsetsThirdExample,
        openWindowsInsetsFourthExample = openWindowsInsetsFourthExample,
        openFocusFormExample = openFocusFormExample,
        openBringToViewExample = openBringToViewExample,
        openBonusLayoutExample = openBonusLayoutExample,
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUIState,
    modifier: Modifier = Modifier,
    openWindowsInsetsFirstExample: () -> Unit = {},
    openWindowsInsetsSecondExample: () -> Unit = {},
    openWindowsInsetsThirdExample: () -> Unit = {},
    openWindowsInsetsFourthExample: () -> Unit = {},
    openFocusFormExample: () -> Unit = {},
    openBringToViewExample: () -> Unit = {},
    openBonusLayoutExample: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.surface)
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.systemBars),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState()),
        ) {
            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.windows_insets_1_example),
                onClick = openWindowsInsetsFirstExample,
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.windows_insets_2_example),
                onClick = openWindowsInsetsSecondExample,
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.windows_insets_3_example),
                onClick = openWindowsInsetsThirdExample,
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.windows_insets_4_example),
                onClick = openWindowsInsetsFourthExample,
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.focus_form),
                onClick = openFocusFormExample,
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.bring_to_view),
                onClick = openBringToViewExample,
            )

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.bonus_layout),
                containerColor = KColor.secondaryContainer,
                contentColor = KColor.onSecondaryContainer,
                onClick = openBonusLayoutExample,
            )
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
