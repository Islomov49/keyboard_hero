@file:OptIn(ExperimentalFoundationApi::class)

package uz.demo.keyboardhero.ui.screens.insets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.component.input.KTextField
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme
import uz.demo.keyboardhero.utils.fake.TextGenerator
import uz.demo.keyboardhero.utils.insets.Screen

@Composable
fun WindowsInsetsRoute(
    modifier: Modifier = Modifier,
    viewModel: WindowsInsetsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WindowsInsetsScreen(
        uiState = uiState,
        onTextChange = viewModel::onTextChange,
    )
}

@Composable
fun WindowsInsetsScreen(
    uiState: WindowsInsetsUIState,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.surface),
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

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
                .background(KColor.primaryContainer, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier,
                text = uiState.loremIpsum,
                fontSize = 16.sp,
                color = KColor.onPrimaryContainer,
            )
        }
    }
}

@Preview(device = Devices.PIXEL_7, showSystemUi = true)
@Composable
private fun PreviewWindowsInsets() {
    val loremIpsum = TextGenerator.generateText(500)
    KeyboardHeroTheme {
        KBackground {
            var uiState by remember {
                mutableStateOf(WindowsInsetsUIState())
            }
            WindowsInsetsScreen(
                uiState = WindowsInsetsUIState(
                    loremIpsum = loremIpsum,
                ),
                onTextChange = {
                    uiState = uiState.copy(text = it)
                },
            )
        }
    }
}
