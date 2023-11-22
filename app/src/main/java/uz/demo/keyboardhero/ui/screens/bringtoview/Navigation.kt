package uz.demo.keyboardhero.ui.screens.bringtoview

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val BringToViewRoute = "BringToView_route"

fun NavController.navigateToBringToView(navOptions: NavOptions? = null) {
    this.navigate(
        route = BringToViewRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.bringToViewScreen(
    onBack: () -> Unit,
) {
    composableWithScaleAlphaAnimation(route = BringToViewRoute) {
        BringToViewRoute(onBack = onBack)
    }
}
