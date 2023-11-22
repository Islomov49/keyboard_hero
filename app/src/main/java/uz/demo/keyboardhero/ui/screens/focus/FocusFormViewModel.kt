package uz.demo.keyboardhero.ui.screens.focus

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import uz.demo.keyboardhero.utils.base.BaseViewModel

@Immutable
data class FocusFormUIState(
    val userId: String = "",
    val fullName: String = "",
    val accountId: String = "",
    val secretWord: String = "",
    val loading: Boolean = false,
) {
    val isValidUserId = userId.length > 5
    val showInvalidUserIdError = !isValidUserId
}

sealed interface FocusFormEvents {
    data object CloseKeyboardIfSecretWordFocused : FocusFormEvents
}

class FocusFormViewModel : BaseViewModel() {

    val uiState: MutableStateFlow<FocusFormUIState> = MutableStateFlow(FocusFormUIState())
    val events: MutableSharedFlow<FocusFormEvents> = MutableSharedFlow()

    init {
        uiState.update {
            it.copy(
                userId = "334584",
                accountId = "User-23",
            )
        }
    }

    fun onUserIdChange(value: String) {
        uiState.update {
            it.copy(userId = value)
        }
    }

    fun onFullNameChange(value: String) {
        uiState.update {
            it.copy(fullName = value)
        }
    }

    fun onAccountIdChange(value: String) {
        uiState.update {
            it.copy(accountId = value)
        }
    }

    fun onSecretWordChange(value: String) {
        uiState.update {
            it.copy(secretWord = value)
        }
    }
}
