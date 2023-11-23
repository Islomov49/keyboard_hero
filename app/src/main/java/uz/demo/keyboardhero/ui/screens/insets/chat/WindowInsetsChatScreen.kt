@file:OptIn(
    ExperimentalLayoutApi::class,
    ExperimentalLayoutApi::class,
    ExperimentalFoundationApi::class,
)

package uz.demo.keyboardhero.ui.screens.insets.chat

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
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
import uz.demo.keyboardhero.utils.insets.Screen

@Composable
fun WindowInsetsChatRoute(
    modifier: Modifier = Modifier,
    viewModel: WindowInsetsAdvancedViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    WindowInsetsChatScreen(
        uiState = uiState,
        modifier = modifier,
        onTextChange = viewModel::onTextChange,
    )
}

@Composable
fun WindowInsetsChatScreen(
    uiState: WindowInsetsAdvancedUIState,
    modifier: Modifier = Modifier,
    onTextChange: (String) -> Unit = {},
) {
    val listState = rememberLazyListState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.surface)
            .windowInsetsPadding(WindowInsets.Screen),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 70.dp)
                .consumeWindowInsets(PaddingValues(bottom = 70.dp))
                .background(KColor.surfaceDim)
                .windowInsetsPadding(WindowInsets.safeDrawing),
        ) {
            Toolbar(
                text = stringResource(R.string.windows_insets_awesome_example),
                modifier = Modifier.fillMaxWidth(),
            )

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .weight(1f),
                reverseLayout = true,
            ) {
                items(
                    items = uiState.chats,
                    key = { it.id },
                ) {
                    ChatMessageItem(
                        modifier = Modifier.fillMaxWidth(),
                        uiState = it,
                    )

                    Spacer(modifier = Modifier.height(24.dp))
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(3.dp, RoundedCornerShape(16.dp))
                    .background(KColor.surface, RoundedCornerShape(16.dp))
                    .padding(8.dp),
            ) {
                KTextField(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 60.dp),
                    value = uiState.text,
                    hint = stringResource(R.string.awesome_hint),
                    onValueChange = onTextChange,
                )

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(KColor.primaryContainer, RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .clickable { },
                ) {
                    Icon(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center),
                        imageVector = Icons.AutoMirrored.Rounded.Send,
                        contentDescription = null,
                        tint = KColor.onPrimaryContainer,
                    )
                }
            }
        }

        BottomBar(
            modifier = Modifier.Companion
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(70.dp),
        )
    }
}

@Composable
private fun ChatMessageItem(
    uiState: ChatMessages,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier
            .padding(
                if (uiState.incomeMessage) {
                    PaddingValues(end = 100.dp)
                } else {
                    PaddingValues(start = 100.dp)
                },
            )
            .background(
                if (uiState.incomeMessage) {
                    KColor.secondary
                } else {
                    KColor.primary
                },
                RoundedCornerShape(8.dp),
            )
            .padding(8.dp),
        text = uiState.displayText,
        fontSize = 16.sp,
        color = if (uiState.incomeMessage) {
            KColor.onSecondary
        } else {
            KColor.onPrimary
        },
        textAlign = if (uiState.incomeMessage) {
            TextAlign.Start
        } else {
            TextAlign.End
        },
    )
}

@Composable
private fun Toolbar(text: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(KColor.surface)
            .padding(16.dp),
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = KColor.onSurface,
        )
    }
}

@Composable
private fun BottomBar(modifier: Modifier) {
    Row(
        modifier = modifier
            .background(KColor.surfaceBright)
            .padding(8.dp),
    ) {
        Icon(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .clickable {}
                .padding(8.dp),
            imageVector = Icons.Outlined.Home,
            contentDescription = null,
            tint = KColor.surfaceVariant,
        )
        Icon(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .clickable {}
                .padding(8.dp),
            imageVector = Icons.Outlined.Email,
            contentDescription = null,
            tint = KColor.primary,
        )
        Icon(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(16.dp))
                .clickable {}
                .padding(8.dp),
            imageVector = Icons.Outlined.Settings,
            contentDescription = null,
            tint = KColor.surfaceVariant,
        )
    }
}

@Preview
@Composable
private fun PreviewWindowInsetsAdvanced() {
    KeyboardHeroTheme {
        KBackground {
            WindowInsetsChatScreen(
                uiState = WindowInsetsAdvancedUIState(),
            )
        }
    }
}
