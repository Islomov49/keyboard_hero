package uz.demo.keyboardhero.ui.screens.insets

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import uz.demo.keyboardhero.utils.base.BaseViewModel
import uz.demo.keyboardhero.utils.fake.TextGenerator

@Immutable
data class WindowsInsetsUIState(
    val text: String = "",
    val loremIpsum: String = "",
    val loading: Boolean = false,
)

sealed interface WindowsInsetsEvents

class WindowsInsetsViewModel : BaseViewModel() {

    val uiState: MutableStateFlow<WindowsInsetsUIState> = MutableStateFlow(WindowsInsetsUIState())
    val events: MutableSharedFlow<WindowsInsetsEvents> = MutableSharedFlow()

    init {
        uiState.update {
            it.copy(loremIpsum = TextGenerator.generateText(500))
        }
    }

    fun onTextChange(value: String) {
        uiState.update {
            it.copy(text = value)
        }
    }
}
