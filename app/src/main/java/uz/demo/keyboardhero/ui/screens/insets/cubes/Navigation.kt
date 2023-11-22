package uz.demo.keyboardhero.ui.screens.insets.cubes

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val WindowInsetsCubeRoute = "WindowInsetsCube_route"

fun NavController.navigateToWindowInsetsCube(navOptions: NavOptions? = null) {
    this.navigate(
        route = WindowInsetsCubeRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.windowInsetsCubeScreen() {
    composableWithScaleAlphaAnimation(route = WindowInsetsCubeRoute) {
        WindowInsetsCubeRoute()
    }
}
