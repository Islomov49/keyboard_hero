package uz.demo.keyboardhero.ui.screens

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import uz.demo.keyboardhero.utils.navigation.composableWithScaleAlphaAnimation

const val HomeScreenRoute = "HomeScreen_route"

fun NavController.navigateToHomeScreen(navOptions: NavOptions? = null) {
    this.navigate(
        route = HomeScreenRoute,
        navOptions = navOptions,
    )
}

fun NavGraphBuilder.homeScreenScreen(
    openWindowsInsetsFirstExample: () -> Unit,
    openWindowsInsetsSecondExample: () -> Unit,
    openWindowsInsetsThirdExample: () -> Unit,
    openWindowsInsetsFourthExample: () -> Unit,
    openFocusFormExample: () -> Unit,
    openBringToViewExample: () -> Unit,
    openBonusLayoutExample: () -> Unit,
) {
    composableWithScaleAlphaAnimation(route = HomeScreenRoute) {
        HomeRoute(
            openWindowsInsetsFirstExample = openWindowsInsetsFirstExample,
            openWindowsInsetsSecondExample = openWindowsInsetsSecondExample,
            openWindowsInsetsThirdExample = openWindowsInsetsThirdExample,
            openWindowsInsetsFourthExample = openWindowsInsetsFourthExample,
            openFocusFormExample = openFocusFormExample,
            openBringToViewExample = openBringToViewExample,
            openBonusLayoutExample = openBonusLayoutExample,
        )
    }
}
