package uz.demo.keyboardhero.ui.screens.insets

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val WindowsInsetsRoute = "WindowsInsets_route"

fun NavController.navigateToWindowsInsets(navOptions: NavOptions? = null) {
    this.navigate(
        route = WindowsInsetsRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.windowsInsetsScreen() {
    composableWithScaleAlphaAnimation(route = WindowsInsetsRoute) {
        WindowsInsetsRoute()
    }
}
