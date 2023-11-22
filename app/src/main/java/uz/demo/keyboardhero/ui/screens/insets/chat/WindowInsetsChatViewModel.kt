package uz.demo.keyboardhero.ui.screens.insets.chat

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import uz.demo.keyboardhero.utils.base.BaseViewModel
import uz.demo.keyboardhero.utils.fake.TextGenerator

@Immutable
data class WindowInsetsAdvancedUIState(
    val chats: ImmutableList<ChatMessages> = persistentListOf(),
    val text: String = "",
    val loading: Boolean = false,
)

@Immutable
data class ChatMessages(
    val id: Int = 0,
    val text: String = "",
    val incomeMessage: Boolean = false,
) {
    val displayText = "${id + 1}. $text"
}

sealed interface WindowInsetsAdvancedEvents

class WindowInsetsAdvancedViewModel : BaseViewModel() {

    val uiState: MutableStateFlow<WindowInsetsAdvancedUIState> =
        MutableStateFlow(WindowInsetsAdvancedUIState())
    val events: MutableSharedFlow<WindowInsetsAdvancedEvents> = MutableSharedFlow()

    init {
        uiState.update {
            it.copy(
                chats = generateChatMessages(20),
            )
        }
    }

    private fun generateChatMessages(count: Int): ImmutableList<ChatMessages> {
        return (0..count).map {
            ChatMessages(
                id = it,
                text = TextGenerator.generateText((10..20).random()),
                incomeMessage = it % 2 == 0,
            )
        }.toPersistentList()
    }

    fun onTextChange(value: String) {
        uiState.update {
            it.copy(
                text = value,
            )
        }
    }
}
