@file:OptIn(ExperimentalFoundationApi::class)

package uz.demo.keyboardhero.ui.screens.bringtoview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.component.buttons.KButton
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.component.input.KTextField
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme

@Composable
fun BringToViewRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BringToViewViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val bringToViewEmail = remember { BringIntoViewRequester() }
    val bringToViewSwitch = remember { BringIntoViewRequester() }

    LaunchedEffect(true) {
        viewModel.events.collect { event ->
            when (event) {
                BringToViewEvents.BringIntoViewEmail -> {
                    bringToViewEmail.bringIntoView()
                }
                BringToViewEvents.BringIntoViewSwitch -> {
                    bringToViewSwitch.bringIntoView()
                }
                BringToViewEvents.CloseWhenSuccess -> {
                    onBack()
                }
            }
        }
    }

    BringToViewScreen(
        uiState = uiState,
        bringToViewEmail = bringToViewEmail,
        bringToViewSwitch = bringToViewSwitch,
        modifier = modifier,
        onEmailUpdate = viewModel::onEmailUpdate,
        onSubmitClicked = viewModel::onSubmitClicked,
        onCheckedChange = viewModel::onCheckedChange,
    )
}

@Composable
fun BringToViewScreen(
    uiState: BringToViewUIState,
    bringToViewEmail: BringIntoViewRequester,
    bringToViewSwitch: BringIntoViewRequester,
    modifier: Modifier = Modifier,
    onEmailUpdate: (String) -> Unit = {},
    onCheckedChange: (Boolean) -> Unit = {},
    onSubmitClicked: () -> Unit = {},
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.background)
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Column(
            modifier = Modifier
                .padding(bottom = 60.dp)
                .verticalScroll(rememberScrollState())
                .padding(bottom = 8.dp),
        ) {
            Text(text = stringResource(R.string.user_agreement_form), fontSize = 24.sp)

            Spacer(modifier = Modifier.height(24.dp))

            Column(
                modifier = Modifier.bringIntoViewRequester(bringToViewEmail),
            ) {
                KTextField(
                    hint = stringResource(R.string.user_email),
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.userEmail,
                    onValueChange = onEmailUpdate,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Email,
                    ),
                )

                Spacer(modifier = Modifier.height(8.dp))

                if (!uiState.isValidEmail) {
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = stringResource(R.string.invalid_email_address),
                        color = KColor.error,
                        fontSize = 16.sp,
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                modifier = Modifier,
                text = uiState.userAgreementText,
                fontSize = 16.sp,
                color = KColor.onSurface,
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .bringIntoViewRequester(bringToViewSwitch)
                    .fillMaxWidth()
                    .padding(bottom = 24.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        onCheckedChange(!uiState.checkedAgreement)
                    }
                    .padding(8.dp),
            ) {
                Switch(
                    modifier = Modifier.size(50.dp),
                    checked = uiState.checkedAgreement,
                    onCheckedChange = onCheckedChange,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.i_agree),
                    fontSize = 16.sp,
                    color = KColor.onSurface,
                )
            }
        }

        KButton(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            text = stringResource(R.string.submit),
            onClick = onSubmitClicked,
        )
    }
}

@Preview
@Composable
private fun PreviewBringToView() {
    KeyboardHeroTheme {
        KBackground {
            val bringToViewEmail = remember { BringIntoViewRequester() }
            val bringToViewSwitch = remember { BringIntoViewRequester() }

            BringToViewScreen(
                uiState = BringToViewUIState(),
                bringToViewEmail = bringToViewEmail,
                bringToViewSwitch = bringToViewSwitch,
            )
        }
    }
}
