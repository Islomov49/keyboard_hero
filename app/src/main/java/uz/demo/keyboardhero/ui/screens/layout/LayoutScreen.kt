@file:OptIn(ExperimentalFoundationApi::class)

package uz.demo.keyboardhero.ui.screens.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.component.buttons.KButton
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.component.input.KPasswordTextField
import uz.demo.keyboardhero.ui.component.input.KTextField
import uz.demo.keyboardhero.ui.component.layout.AdaptiveScrollScreen
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme

@Composable
fun LayoutRoute(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LayoutViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        viewModel.events.collect { event ->
            when (event) {
                LayoutEvents.CloseWhenSuccess -> {
                    onBack()
                }
            }
        }
    }

    LayoutScreen(
        uiState = uiState,
        modifier = modifier,
        onFullNameChange = viewModel::onFullNameChange,
        onEmailChange = viewModel::onEmailChange,
        onPasswordChange = viewModel::onPasswordChange,
        onRepeatPasswordChange = viewModel::onRepeatPasswordChange,
        onAgreementAccepted = viewModel::onAgreementAccepted,
        onSubmitClicked = viewModel::onSubmitClicked,
    )
}

@Composable
fun LayoutScreen(
    uiState: LayoutUIState,
    modifier: Modifier = Modifier,
    onFullNameChange: (String) -> Unit = {},
    onEmailChange: (String) -> Unit = {},
    onPasswordChange: (String) -> Unit = {},
    onRepeatPasswordChange: (String) -> Unit = {},
    onAgreementAccepted: (Boolean) -> Unit = {},
    onSubmitClicked: () -> Unit = {},
) {
    val bringToViewFullName = remember { BringIntoViewRequester() }
    val bringToViewEmail = remember { BringIntoViewRequester() }
    val bringToViewPassword = remember { BringIntoViewRequester() }
    val bringToViewRepeatPassword = remember { BringIntoViewRequester() }
    val bringToViewAgreement = remember { BringIntoViewRequester() }

    AdaptiveScrollScreen(
        modifier = modifier,
        contentPadding = 16.dp,
        top = {
            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.focus_form),
                fontSize = 36.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(modifier = Modifier.height(16.dp))

            val errorFullNameMessage = if (uiState.showFullNameEmptyError) {
                stringResource(R.string.please_enter_your_full_name)
            } else {
                ""
            }

            KTextField(
                hint = stringResource(R.string.full_name),
                modifier = Modifier.fillMaxWidth(),
                value = uiState.fullName,
                onValueChange = onFullNameChange,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Text,
                    capitalization = KeyboardCapitalization.Words,
                ),
                bringToView = bringToViewFullName,
                errorMessage = errorFullNameMessage,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .bringIntoViewRequester(bringToViewEmail)
                    .padding(vertical = 8.dp),
            ) {
                val errorEmailMessage = if (uiState.showInvalidEmailError) {
                    stringResource(R.string.invalid_email_address)
                } else if (uiState.showUserExistsError) {
                    stringResource(R.string.user_exists)
                } else {
                    ""
                }

                KTextField(
                    hint = stringResource(R.string.user_email),
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email,
                    ),
                    bringToView = bringToViewEmail,
                    errorMessage = errorEmailMessage,
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.email_description),
                    fontSize = 12.sp,
                    color = KColor.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .bringIntoViewRequester(bringToViewPassword)
                    .padding(vertical = 8.dp),
            ) {
                val errorPasswordMessage = if (uiState.showInvalidPasswordError) {
                    stringResource(R.string.minimal_password_error)
                } else {
                    ""
                }

                KPasswordTextField(
                    hint = stringResource(R.string.password),
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    bringToView = bringToViewPassword,
                    errorMessage = errorPasswordMessage,
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.password_description),
                    fontSize = 12.sp,
                    color = KColor.onSurfaceVariant,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .bringIntoViewRequester(bringToViewRepeatPassword)
                    .padding(vertical = 8.dp),
            ) {
                val errorPasswordMessage = if (uiState.showNotSamePasswordError) {
                    stringResource(R.string.repeated_password_is_not_the_same)
                } else {
                    ""
                }

                KPasswordTextField(
                    hint = stringResource(R.string.please_repeat_password),
                    modifier = Modifier.fillMaxWidth(),
                    value = uiState.repeatPassword,
                    imeAction = ImeAction.Next,
                    onValueChange = onRepeatPasswordChange,
                    bringToView = bringToViewRepeatPassword,
                    errorMessage = errorPasswordMessage,
                )

                Text(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    text = stringResource(id = R.string.password_repeated_description),
                    fontSize = 12.sp,
                    color = KColor.onSurfaceVariant,
                )
            }
        },
        bottom = {
            LaunchedEffect(uiState.showAgreementNotAcceptedError) {
                if (uiState.showAgreementNotAcceptedError) {
                    bringToViewAgreement.bringIntoView()
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        onAgreementAccepted(!uiState.agreementAccepted)
                    }
                    .padding(8.dp)
                    .bringIntoViewRequester(bringToViewAgreement),
            ) {
                Switch(
                    modifier = Modifier.size(50.dp),
                    checked = uiState.agreementAccepted,
                    onCheckedChange = onAgreementAccepted,
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(R.string.i_agree),
                    fontSize = 12.sp,
                    color = if (uiState.showAgreementNotAcceptedError) KColor.error else KColor.onSurface,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            KButton(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(R.string.submit),
                onClick = onSubmitClicked,
            )
        },
    )
}

@Preview
@Composable
private fun PreviewLayout() {
    KeyboardHeroTheme {
        KBackground {
            LayoutScreen(
                uiState = LayoutUIState(),
            )
        }
    }
}
