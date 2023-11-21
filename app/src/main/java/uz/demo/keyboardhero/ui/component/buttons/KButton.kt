package uz.demo.keyboardhero.ui.component.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.demo.keyboardhero.ui.component.cover.KBackground
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme
import uz.demo.keyboardhero.utils.click.MultipleEventsCutter
import uz.demo.keyboardhero.utils.click.get

@Composable
fun KButton(
    modifier: Modifier = Modifier,
    text: String = "",
    loading: Boolean = false,
    enabled: Boolean = true,
    containerColor: Color? = null,
    contentColor: Color? = null,
    elevation: Dp = 1.dp,
    onClick: () -> Unit = {},
    debounce: Boolean = true,
    preContent: @Composable RowScope.() -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    val multipleEventsCutter = remember(debounce) { MultipleEventsCutter.get(debounce) }

    val buttonContainerColor = containerColor ?: KColor.primaryContainer
    val buttonContentColor = contentColor ?: KColor.onPrimaryContainer
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonContainerColor,
            contentColor = buttonContentColor,
            disabledContainerColor = KColor.outline,
            disabledContentColor = KColor.onSurfaceVariant,
        ),
        shape = RoundedCornerShape(12.dp),
        onClick = {
            if (!loading) {
                multipleEventsCutter.processEvent { onClick() }
            }
        },
        enabled = enabled,
        modifier = modifier
            .height(60.dp)
            .alpha(if (enabled) 1f else 0.5f),
        elevation = ButtonDefaults.buttonElevation(defaultElevation = elevation),
    ) {
        preContent()

        if (loading) {
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp),
                color = buttonContentColor,
                strokeWidth = 2.dp,
            )
        } else {
            Text(
                text = text,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
            )
        }

        content()
    }
}

@Preview(name = "Button", widthDp = 400, heightDp = 300)
@Composable
fun PreviewNButton() {
    KeyboardHeroTheme {
        KBackground {
            Column(modifier = Modifier.padding(16.dp)) {
                KButton(
                    text = "Button",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                )

                Spacer(modifier = Modifier.height(16.dp))

                KButton(
                    text = "Button",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    enabled = false,
                )

                Spacer(modifier = Modifier.height(16.dp))

                KButton(
                    text = "Button",
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    loading = true,
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
