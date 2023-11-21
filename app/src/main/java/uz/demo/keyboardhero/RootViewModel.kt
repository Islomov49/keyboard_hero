package uz.demo.keyboardhero

import androidx.compose.runtime.Immutable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import uz.demo.keyboardhero.utils.base.BaseViewModel

@Immutable
data class RootViewUIState(
    val loading: Boolean = true
)

class RootViewModel : BaseViewModel() {
    val uiState: MutableStateFlow<RootViewUIState> = MutableStateFlow(RootViewUIState())

    init {
        vmScope.launch {
            withTimeout(1000) {
                // try to fetch main screen data
                delay(10_000)
            }
        }.invokeOnCompletion {
            uiState.update { it.copy(loading = false) }
        }
    }
}
