package uz.demo.keyboardhero.utils.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.TransformOrigin
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composableWithScaleAlphaAnimation(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    val animationSpecEnter = tween<Float>(80)
    val animationSpecExit = tween<Float>(160)

    composable(
        route = route,
        arguments = arguments,
        enterTransition = {
            fadeIn(animationSpec = animationSpecEnter, 0.5f) + scaleIn(
                animationSpecEnter,
                initialScale = 0.95f,
                transformOrigin = TransformOrigin(0.5f, 0.55f)
            )
        },
        exitTransition = {
            fadeOut(animationSpec = animationSpecExit, 0f) + scaleOut(
                animationSpec = animationSpecExit,
                targetScale = 1.05f,
                transformOrigin = TransformOrigin(0.5f, 0.45f)
            )
        },
        popEnterTransition = {
            fadeIn(animationSpec = animationSpecEnter, 0.5f) + scaleIn(
                animationSpecEnter,
                initialScale = 0.95f,
                transformOrigin = TransformOrigin(0.5f, 0.55f)
            )
        },
        popExitTransition = {
            fadeOut(animationSpec = animationSpecExit, 0f) + scaleOut(
                animationSpec = animationSpecExit,
                targetScale = 1.05f,
                transformOrigin = TransformOrigin(0.5f, 0.45f)
            )
        }
    ) {
        content.invoke(it)
    }
}

fun NavGraphBuilder.composableWithoutAnimation(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        arguments = arguments,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }
    ) {
        content.invoke(it)
    }
}
