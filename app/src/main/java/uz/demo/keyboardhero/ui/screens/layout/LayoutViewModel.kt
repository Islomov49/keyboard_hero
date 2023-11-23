package uz.demo.keyboardhero.ui.screens.layout

import android.util.Patterns
import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.demo.keyboardhero.utils.base.BaseViewModel

@Immutable
data class LayoutUIState(
    val fullName: String = "",
    val email: String = "",
    val password: String = "",
    val repeatPassword: String = "",
    val agreementAccepted: Boolean = false,
    val showFullNameEmptyError: Boolean = false,
    val showInvalidEmailError: Boolean = false,
    val showUserExistsError: Boolean = false,
    val showInvalidPasswordError: Boolean = false,
    val showNotSamePasswordError: Boolean = false,
    val showAgreementNotAcceptedError: Boolean = false,
) {
    val isFullNameEmpty = fullName.isEmpty()

    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

    val isPasswordTheSame = password.isNotEmpty() && password == repeatPassword

    val isValidPassword = password.length >= 6
}

sealed interface LayoutEvents {
    data object CloseWhenSuccess : LayoutEvents
}

class LayoutViewModel : BaseViewModel() {

    val uiState: MutableStateFlow<LayoutUIState> = MutableStateFlow(LayoutUIState())
    val events: MutableSharedFlow<LayoutEvents> = MutableSharedFlow()

    fun onFullNameChange(value: String) {
        uiState.update {
            it.copy(
                fullName = value,
                showFullNameEmptyError = false,
            )
        }
    }

    fun onEmailChange(value: String) {
        uiState.update {
            it.copy(
                email = value,
                showInvalidEmailError = false,
            )
        }
    }

    fun onPasswordChange(value: String) {
        uiState.update {
            it.copy(
                password = value,
                showInvalidPasswordError = false,
                showNotSamePasswordError = false,
            )
        }
    }

    fun onRepeatPasswordChange(value: String) {
        uiState.update {
            it.copy(
                repeatPassword = value,
                showInvalidPasswordError = false,
                showNotSamePasswordError = false,
            )
        }
    }

    fun onAgreementAccepted(value: Boolean) {
        uiState.update {
            it.copy(
                agreementAccepted = value,
                showAgreementNotAcceptedError = false,
            )
        }
    }

    fun onSubmitClicked() {
        val state = uiState.value

        if (state.isFullNameEmpty) {
            uiState.update {
                it.copy(
                    showFullNameEmptyError = true,
                )
            }
            return
        }

        if (!state.isEmailValid) {
            uiState.update {
                it.copy(
                    showInvalidEmailError = true,
                )
            }
            return
        }

        if (!state.isValidPassword) {
            uiState.update {
                it.copy(
                    showInvalidPasswordError = true,
                )
            }
            return
        }

        if (!state.isPasswordTheSame) {
            uiState.update {
                it.copy(
                    showNotSamePasswordError = true,
                )
            }
            return
        }

        if (!state.agreementAccepted) {
            uiState.update {
                it.copy(
                    showAgreementNotAcceptedError = true,
                )
            }
            return
        }

        vmScope.launch {
            // create user
            events.emit(LayoutEvents.CloseWhenSuccess)
        }
    }
}
