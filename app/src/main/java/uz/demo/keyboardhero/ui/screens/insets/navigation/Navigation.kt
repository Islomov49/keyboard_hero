package uz.demo.keyboardhero.ui.screens.insets.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val NavigationWithoutGlitchRoute = "NavigationWithoutGlitch_route"

fun NavController.navigateToNavigationWithoutGlitch(navOptions: NavOptions? = null) {
    this.navigate(
        route = NavigationWithoutGlitchRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.navigationWithoutGlitchScreen(
    openCenteredScreen: () -> Unit,
) {
    composableWithScaleAlphaAnimation(route = NavigationWithoutGlitchRoute) {
        NavigationWithoutGlitchRoute(
            openCenteredScreen = openCenteredScreen,
        )
    }
}
