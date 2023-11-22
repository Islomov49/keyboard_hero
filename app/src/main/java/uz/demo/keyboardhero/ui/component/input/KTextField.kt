package uz.demo.keyboardhero.ui.component.input

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carwon.car.utils.state.rememberPrevious
import kotlinx.coroutines.launch
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme
import uz.demo.keyboardhero.utils.compose.DarkPreview
import uz.demo.keyboardhero.utils.compose.LightPreview

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun KTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    boxModifier: Modifier? = null,
    hint: String = "",
    errorMessage: String = "",
    acceptRegex: Regex? = null,
    trimPaste: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    fontSize: TextUnit = 16.sp,
    dynamicHint: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    borderFocusedColor: Color = KColor.primary,
    cursorBrush: Brush = SolidColor(KColor.onSurface),
    onFocusChanged: (Boolean) -> Unit = {},
    contentCompose: @Composable RowScope.() -> Unit = {},
) {
    val bringToView = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()
    val prevValue = rememberPrevious(errorMessage)

    val labelOffsetX = remember { Animatable(if (value.isNotEmpty() && dynamicHint) 1f else 0f) }

    var hasFocused by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(errorMessage, prevValue) {
        if (errorMessage.isNotEmpty()) {
            bringToView.bringIntoView()
        }
    }

    LaunchedEffect(value.isNotEmpty(), dynamicHint) {
        if (dynamicHint) {
            labelOffsetX.animateTo(
                targetValue = if (value.isNotEmpty()) 1f else 0f,
            )
        }
    }

    BasicTextField(
        value = value,
        onValueChange = {
            val normalized = if (trimPaste) it.trim(' ', '\n', '\t') else it
            if (acceptRegex != null && !normalized.contains(acceptRegex)) {
                return@BasicTextField
            }
            onValueChange(normalized)
        },
        modifier = modifier
            .bringIntoViewRequester(bringToView)
            .onFocusChanged {
                hasFocused = it.isFocused
                coroutineScope.launch {
                    onFocusChanged(it.isFocused)
                    if (it.isFocused) {
                        bringToView.bringIntoView()
                    }
                }
            },
        keyboardActions = keyboardActions,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        singleLine = singleLine,
        textStyle = TextStyle(
            color = if (errorMessage.isEmpty()) {
                KColor.onSurface
            } else {
                KColor.onErrorContainer
            },
            fontSize = fontSize,
        ),
        cursorBrush = cursorBrush,
        decorationBox = { innerTextField ->

            Column(modifier = Modifier.fillMaxWidth()) {
                val backgroundColor = when {
                    errorMessage.isNotEmpty() -> KColor.errorContainer
                    hasFocused -> Color.Transparent
                    else -> KColor.surfaceContainer
                }
                val borderColor = when {
                    errorMessage.isNotEmpty() && hasFocused -> KColor.onErrorContainer
                    hasFocused -> borderFocusedColor
                    else -> Color.Transparent
                }

                Box(
                    modifier = (
                        boxModifier ?: Modifier
                            .fillMaxWidth()
                            .height(60.dp)
                            .clip(RoundedCornerShape(12.dp))
                        )
                        .background(color = backgroundColor)
                        .border(
                            width = 1.dp,
                            color = borderColor,
                            shape = RoundedCornerShape(12.dp),
                        )
                        .padding(horizontal = 16.dp, vertical = 18.dp),
                ) {
                    if (dynamicHint || value.isEmpty()) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.CenterStart)
                                .offset(0.dp, (-12 * labelOffsetX.value).dp),
                            text = hint,
                            style = TextStyle(
                                color = if (errorMessage.isEmpty()) {
                                    KColor.onSurfaceVariant
                                } else {
                                    KColor.onErrorContainer
                                },
                                fontSize = (fontSize.value - 2 * labelOffsetX.value).sp,
                            ),
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterStart),
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .offset(0.dp, (8 * labelOffsetX.value).dp),
                        ) {
                            innerTextField()
                        }
                        contentCompose()
                    }
                }

                if (errorMessage.isNotEmpty()) {
                    Box(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    ) {
                        Text(
                            text = errorMessage,
                            fontSize = 14.sp,
                            color = KColor.error,
                            modifier = Modifier.fillMaxWidth(),
                        )
                    }
                }
            }
        },
    )
}

@DarkPreview
@LightPreview
@Composable
fun PreviewBackButton() {
    KeyboardHeroTheme {
        KBackground {
            var phoneNumber by remember {
                mutableStateOf(
                    "",
                )
            }
            KTextField(
                modifier = Modifier.height(100.dp),
                value = phoneNumber,
                hint = "this is hint",
                onValueChange = {
                    phoneNumber = it
                },
            )
        }
    }
}
