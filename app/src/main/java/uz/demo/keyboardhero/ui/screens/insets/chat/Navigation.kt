package uz.demo.keyboardhero.ui.screens.insets.chat

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val WindowInsetsAdvancedRoute = "WindowInsetsAdvanced_route"

fun NavController.navigateToWindowInsetsChat(navOptions: NavOptions? = null) {
    this.navigate(
        route = WindowInsetsAdvancedRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.windowInsetsChatScreen() {
    composableWithScaleAlphaAnimation(route = WindowInsetsAdvancedRoute) {
        WindowInsetsChatRoute()
    }
}
