@file:OptIn(
    ExperimentalComposeUiApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class,
)

package uz.demo.keyboardhero.ui.screens.focus

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
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

@Composable
fun FocusFormRoute(
    modifier: Modifier = Modifier,
    viewModel: FocusFormViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    FocusFormScreen(
        uiState = uiState,
        modifier = modifier,
        onUserIdChange = viewModel::onUserIdChange,
        onFullNameChange = viewModel::onFullNameChange,
        onAccountIdChange = viewModel::onAccountIdChange,
        onSecretWordChange = viewModel::onSecretWordChange,
    )
}

@Composable
fun FocusFormScreen(
    uiState: FocusFormUIState,
    modifier: Modifier = Modifier,
    onUserIdChange: (String) -> Unit = {},
    onFullNameChange: (String) -> Unit = {},
    onAccountIdChange: (String) -> Unit = {},
    onSecretWordChange: (String) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current

    val (
        userIdFocusRequester,
        fullNameFocusRequester,
        accountIdFocusRequester,
        secretWordFocusRequester,
    ) = remember { FocusRequester.createRefs() }

    var secretCodeFocused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(uiState.isValidUserId) {
        if (!uiState.isValidUserId) {
            userIdFocusRequester.captureFocus()
        } else {
            userIdFocusRequester.freeFocus()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(KColor.background)
            .padding(16.dp)
            .windowInsetsPadding(WindowInsets.safeDrawing),
    ) {
        Text(text = stringResource(R.string.focus_form), fontSize = 24.sp)

        Spacer(modifier = Modifier.height(24.dp))

        KTextField(
            hint = stringResource(R.string.user_id),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(userIdFocusRequester),
            value = uiState.userId,
            onValueChange = onUserIdChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Number,
            ),
            errorMessage = if (uiState.showInvalidUserIdError) {
                stringResource(R.string.invalid_user_id)
            } else {
                ""
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        KTextField(
            hint = stringResource(R.string.full_name),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(fullNameFocusRequester)
                .focusProperties { next = secretWordFocusRequester },
            value = uiState.fullName,
            onValueChange = onFullNameChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        KTextField(
            hint = stringResource(R.string.account_id),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(accountIdFocusRequester),
            value = uiState.accountId,
            onValueChange = onAccountIdChange,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
            ),
        )

        Spacer(modifier = Modifier.height(16.dp))

        val transformation = remember {
            PasswordVisualTransformation()
        }

        KTextField(
            hint = stringResource(R.string.secret_word),
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(secretWordFocusRequester)
                .onFocusChanged {
                    secretCodeFocused = it.isFocused
                },
            value = uiState.secretWord,
            onValueChange = onSecretWordChange,
            visualTransformation = if (secretCodeFocused) {
                VisualTransformation.None
            } else {
                transformation
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                },
            ),
        )

        LaunchedEffect(Unit) {
            if (uiState.isValidUserId) {
                fullNameFocusRequester.requestFocus()
            } else {
                userIdFocusRequester.requestFocus()
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFocusForm() {
    KeyboardHeroTheme {
        KBackground {
            FocusFormScreen(
                uiState = FocusFormUIState(),
            )
        }
    }
}
