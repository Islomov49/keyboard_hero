package uz.demo.keyboardhero.ui.screens.insets.centered

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val CenteredAdjustNothingRoute = "CenteredAdjustNothing_route"

fun NavController.navigateToCenteredAdjustNothing(navOptions: NavOptions? = null) {
    this.navigate(
        route = CenteredAdjustNothingRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.centeredAdjustNothingScreen() {
    composableWithScaleAlphaAnimation(route = CenteredAdjustNothingRoute) {
        CenteredAdjustNothingRoute()
    }
}
