package uz.demo.keyboardhero.ui.component.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.displayCutout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import uz.demo.keyboardhero.ui.theme.KColor
import uz.demo.keyboardhero.ui.theme.KeyboardHeroTheme
import uz.demo.keyboardhero.utils.compose.dpToPx

@Composable
fun AdaptiveScreen(
    modifier: Modifier = Modifier,
    contentPadding: Dp = 0.dp,
    top: @Composable (ColumnScope.() -> Unit)? = null,
    bottom: @Composable (ColumnScope.() -> Unit)? = null,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(KColor.surface)
            .padding(top = contentPadding, start = contentPadding, end = contentPadding)
            .windowInsetsPadding(WindowInsets.systemBars.union(WindowInsets.displayCutout)),
    ) {
        val boxScope = this

        Box(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.ime),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                AdaptiveLayout(
                    availableHeight = boxScope.maxHeight,
                    modifier = Modifier,
                    topContent = {
                        Column {
                            if (top != null) {
                                top()
                            }
                        }
                    },
                    bottomContent = {
                        Column {
                            if (bottom != null) {
                                bottom()
                                Spacer(modifier = Modifier.height(contentPadding / 2))
                            }
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun AdaptiveLayout(
    availableHeight: Dp,
    modifier: Modifier = Modifier,
    topContent: @Composable () -> Unit = {},
    bottomContent: @Composable () -> Unit = {},
) {
    val safeScreenHeight = availableHeight.dpToPx()

    Layout(
        modifier = modifier,
        contents = listOf(topContent, bottomContent),
    ) { (topContentMeasurables, bottomContentMeasurables), constraints ->

        require(topContentMeasurables.size == 1) {
            "topContentMeasurables should only emit one composable"
        }

        require(bottomContentMeasurables.size == 1) {
            "bottomContentMeasurables should only emit one composable"
        }

        val topContentPlaceable = topContentMeasurables.first().measure(constraints)

        val bottomContentPlaceable = bottomContentMeasurables.first().measure(constraints)

        val contentSummaryHeight = topContentPlaceable.height + bottomContentPlaceable.height
        val contentSummaryWidth =
            topContentPlaceable.width.coerceAtLeast(bottomContentPlaceable.width)

        var middleSpace = safeScreenHeight - contentSummaryHeight
        val minSpace = 16.dp.roundToPx()

        if (middleSpace < minSpace) {
            middleSpace = minSpace
        }

        val parentHeight = contentSummaryHeight + middleSpace

        layout(contentSummaryWidth, parentHeight) {
            topContentPlaceable.place(0, 0)
            bottomContentPlaceable.place(0, topContentPlaceable.height + middleSpace)
        }
    }
}

@Preview
@Composable
private fun PreviewAdaptiveLayout() {
    KeyboardHeroTheme {
        AdaptiveScreen(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Cyan),
            contentPadding = 0.dp,
            top = {
                Column {
                    Box(
                        modifier = Modifier
                            .height(136.dp)
                            .fillMaxWidth()
                            .background(Color.Red),
                    )
                    Box(
                        modifier = Modifier
                            .height(100.dp)
                            .fillMaxWidth()
                            .background(Color.Yellow),
                    )
                }
            },
            bottom = {
                Box(
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                        .background(Color.Blue),
                )
            },
        )
    }
}
