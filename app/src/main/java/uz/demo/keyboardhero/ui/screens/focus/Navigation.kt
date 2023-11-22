package uz.demo.keyboardhero.ui.screens.focus

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val FocusFormRoute = "FocusForm_route"

fun NavController.navigateToFocusForm(navOptions: NavOptions? = null) {
    this.navigate(
        route = FocusFormRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.focusFormScreen() {
    composableWithScaleAlphaAnimation(route = FocusFormRoute) {
        FocusFormRoute()
    }
}
