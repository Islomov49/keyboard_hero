package uz.demo.keyboardhero.ui.screens.insets.cubes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.component.input.KTextField
import uz.demo.keyboardhero.ui.screens.insets.WindowsInsetsUIState
import uz.demo.keyboardhero.ui.screens.insets.WindowsInsetsViewModel
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme
import uz.demo.keyboardhero.utils.insets.Screen

@Composable
fun WindowInsetsCubeRoute(
    modifier: Modifier = Modifier,
    viewModel: WindowsInsetsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WindowInsetsCubeScreen(
        uiState = uiState,
        onTextChange = viewModel::onTextChange,
        modifier = modifier,
    )
}

@Composable
fun WindowInsetsCubeScreen(
    uiState: WindowsInsetsUIState,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.surface)
            .windowInsetsPadding(WindowInsets.Screen),
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        KTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            value = uiState.text,
            hint = stringResource(R.string.awesome_hint),
            onValueChange = onTextChange,
        )

        Box(
            modifier = Modifier
                .consumeWindowInsets(PaddingValues(bottom = 30.dp))
                .windowInsetsPadding(WindowInsets.ime)
                .align(Alignment.BottomStart)
                .padding(start = 20.dp, bottom = 30.dp)
                .size(50.dp)
                .background(KColor.primary, RoundedCornerShape(8.dp)),
        )

        Box(
            modifier = Modifier
                .consumeWindowInsets(PaddingValues(bottom = 90.dp))
                .windowInsetsPadding(WindowInsets.ime)
                .align(Alignment.BottomCenter)
                .padding(bottom = 90.dp)
                .size(50.dp)
                .background(KColor.secondary, RoundedCornerShape(8.dp)),
        )

        Box(
            modifier = Modifier
                .consumeWindowInsets(PaddingValues(bottom = 140.dp))
                .windowInsetsPadding(WindowInsets.ime)
                .align(Alignment.BottomEnd)
                .padding(end = 20.dp, bottom = 140.dp)
                .size(50.dp)
                .background(KColor.tertiary, RoundedCornerShape(8.dp)),
        )
    }
}

@Preview
@Composable
private fun PreviewWindowInsetsCubeScreen() {
    KeyboardHeroTheme {
        KBackground {
            var uiState by remember {
                mutableStateOf(WindowsInsetsUIState())
            }
            WindowInsetsCubeScreen(
                uiState = WindowsInsetsUIState(),
                onTextChange = {
                    uiState = uiState.copy(text = it)
                },
            )
        }
    }
}
