package uz.demo.keyboardhero.ui.screens

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import uz.demo.keyboardhero.utils.base.BaseViewModel

@Immutable
data class HomeUIState(
    val loading: Boolean = false
)

sealed interface HomeEvents

class HomeViewModel : BaseViewModel() {

    val uiState: MutableStateFlow<HomeUIState> = MutableStateFlow(HomeUIState())
    val events: MutableSharedFlow<HomeEvents> = MutableSharedFlow()
}
