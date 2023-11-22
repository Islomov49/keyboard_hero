package uz.demo.keyboardhero.ui.screens.bringtoview

import android.util.Patterns
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.demo.keyboardhero.utils.base.BaseViewModel
import uz.demo.keyboardhero.utils.fake.TextGenerator

data class BringToViewUIState(
    val userEmail: String = "",
    val userAgreementText: String = "",
    val checkedAgreement: Boolean = false,
    val loading: Boolean = false,
    val isValidEmail: Boolean = true,
)

sealed interface BringToViewEvents {
    data object BringIntoViewEmail : BringToViewEvents
    data object BringIntoViewSwitch : BringToViewEvents
    data object CloseWhenSuccess : BringToViewEvents
}

class BringToViewViewModel : BaseViewModel() {

    val uiState: MutableStateFlow<BringToViewUIState> = MutableStateFlow(BringToViewUIState())
    val events: MutableSharedFlow<BringToViewEvents> = MutableSharedFlow()

    init {
        uiState.update {
            it.copy(userAgreementText = TextGenerator.generateText(500))
        }
    }

    fun onEmailUpdate(value: String) {
        uiState.update {
            it.copy(userEmail = value, isValidEmail = true)
        }
    }

    fun onSubmitClicked() {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(uiState.value.userEmail).matches()
        val isChecked = uiState.value.checkedAgreement

        if (!isEmailValid) {
            uiState.update {
                it.copy(isValidEmail = false)
            }
            vmScope.launch {
                events.emit(BringToViewEvents.BringIntoViewEmail)
            }
            return
        }

        if (!isChecked) {
            vmScope.launch {
                events.emit(BringToViewEvents.BringIntoViewSwitch)
            }
            return
        }

        vmScope.launch {
            events.emit(BringToViewEvents.CloseWhenSuccess)
        }
    }

    fun onCheckedChange(value: Boolean) {
        uiState.update {
            it.copy(checkedAgreement = value)
        }
    }
}
