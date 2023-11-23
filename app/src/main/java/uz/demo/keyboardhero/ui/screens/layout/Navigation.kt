package uz.demo.keyboardhero.ui.screens.layout

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val LayoutRoute = "Layout_route"

fun NavController.navigateToLayout(navOptions: NavOptions? = null) {
    this.navigate(
        route = LayoutRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.layoutScreen(
    onBack: () -> Unit,
) {
    composableWithScaleAlphaAnimation(route = LayoutRoute) {
        LayoutRoute(
            onBack = onBack,
        )
    }
}
