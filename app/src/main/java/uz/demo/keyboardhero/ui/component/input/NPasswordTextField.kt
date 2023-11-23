@file:OptIn(ExperimentalFoundationApi::class)

package uz.demo.keyboardhero.ui.component.input

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.carwon.car.utils.state.rememberPrevious
import kotlinx.coroutines.launch
import uz.demo.keyboardhero.R
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme
import uz.demo.keyboardhero.utils.filter.InputFilterRegex

@Composable
fun KPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier.fillMaxWidth(),
    boxModifier: Modifier = Modifier
        .fillMaxWidth()
        .height(60.dp),
    hint: String = "",
    errorMessage: String = "",
    imeAction: ImeAction = ImeAction.Next,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    trimPaste: Boolean = false,
    fontSize: TextUnit = 16.sp,
    acceptRegex: Regex? = InputFilterRegex.WithoutCyrillicInput,
    cursorBrush: Brush = SolidColor(KColor.onSurface),
    dynamicHint: Boolean = true,
    onFocused: suspend () -> Unit = {},
    bringToView: BringIntoViewRequester = remember { BringIntoViewRequester() },
    contentCompose: @Composable RowScope.() -> Unit = {},
) {
    val coroutineScope = rememberCoroutineScope()
    val prevValue = rememberPrevious(errorMessage)
    var hasFocused by remember {
        mutableStateOf(false)
    }
    val labelOffsetX = remember { Animatable(if (value.isNotEmpty() && dynamicHint) 1f else 0f) }

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

    var isPasswordVisible by remember { mutableStateOf(false) }
    var content by remember { mutableStateOf(value) }

    val visualTransformation = if (!isPasswordVisible) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }

    BasicTextField(
        value = value,
        onValueChange = {
            val normalized = if (trimPaste) it.trim() else it
            if (acceptRegex != null && !normalized.contains(acceptRegex)) {
                return@BasicTextField
            }
            content = normalized
            onValueChange(normalized)
        },
        modifier = modifier
            .bringIntoViewRequester(bringToView)
            .onFocusChanged {
                hasFocused = it.isFocused
                if (it.isFocused) {
                    coroutineScope.launch {
                        bringToView.bringIntoView()
                        onFocused()
                    }
                }
            },
        keyboardActions = keyboardActions,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = imeAction,
        ),
        singleLine = singleLine,
        visualTransformation = visualTransformation,
        textStyle = TextStyle(
            color = if (errorMessage.isEmpty()) KColor.onBackground else KColor.error,
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
                    errorMessage.isNotEmpty() && hasFocused -> KColor.error
                    hasFocused -> KColor.primary
                    else -> Color.Transparent
                }
                Box(
                    modifier = boxModifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
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
                                color = KColor.onSurfaceVariant,
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
                                .align(Alignment.CenterVertically)
                                .weight(1f)
                                .offset(0.dp, (8 * labelOffsetX.value).dp),
                        ) {
                            innerTextField()
                        }

                        contentCompose()

                        IconButton(
                            modifier = Modifier.align(Alignment.CenterVertically).focusable(false),
                            onClick = { isPasswordVisible = !isPasswordVisible },
                        ) {
                            if (isPasswordVisible) {
                                Icon(
                                    Icons.Outlined.Visibility,
                                    stringResource(R.string.hide_password),
                                    tint = KColor.onBackground,
                                )
                            } else {
                                Icon(
                                    Icons.Outlined.VisibilityOff,
                                    stringResource(R.string.show_password),
                                    tint = KColor.onBackground,
                                )
                            }
                        }
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

@Composable
fun PreviewKPasswordTextField() {
    KeyboardHeroTheme {
        Box {
            val text = "123456"
            KPasswordTextField(
                modifier = Modifier.height(100.dp),
                value = text,
                hint = "this is hint",
                onValueChange = {},
                errorMessage = "hello it is error",
            )
        }
    }
}
