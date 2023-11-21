package uz.demo.keyboardhero.ui.screens

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val HomeScreenRoute = "HomeScreen_route"

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(
        route = HomeScreenRoute,
        navOptions = navOptions
    )
}

fun NavGraphBuilder.homeScreenScreen() {
    composableWithScaleAlphaAnimation(route = HomeScreenRoute) {
        HomeRoute()
    }
}
