package uz.demo.keyboardhero.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import uz.demo.keyboardhero.ui.screens.HomeScreenRoute
import uz.demo.keyboardhero.ui.screens.homeScreenScreen
import uz.demo.keyboardhero.ui.screens.insets.centered.centeredAdjustNothingScreen
import uz.demo.keyboardhero.ui.screens.insets.centered.navigateToCenteredAdjustNothing
import uz.demo.keyboardhero.ui.screens.insets.chat.navigateToWindowInsetsChat
import uz.demo.keyboardhero.ui.screens.insets.chat.windowInsetsChatScreen
import uz.demo.keyboardhero.ui.screens.insets.cubes.navigateToWindowInsetsCube
import uz.demo.keyboardhero.ui.screens.insets.cubes.windowInsetsCubeScreen
import uz.demo.keyboardhero.ui.screens.insets.navigateToWindowsInsets
import uz.demo.keyboardhero.ui.screens.insets.navigation.navigateToNavigationWithoutGlitch
import uz.demo.keyboardhero.ui.screens.insets.navigation.navigationWithoutGlitchScreen
import uz.demo.keyboardhero.ui.screens.insets.windowsInsetsScreen

@Composable
fun RootNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HomeScreenRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        homeScreenScreen(
            openWindowsInsetsFirstExample = navController::navigateToWindowsInsets,
            openWindowsInsetsSecondExample = navController::navigateToNavigationWithoutGlitch,
            openWindowsInsetsThirdExample = navController::navigateToWindowInsetsCube,
            openWindowsInsetsFourthExample = navController::navigateToWindowInsetsChat,
        )

        windowsInsetsScreen()

        windowInsetsCubeScreen()
        navigationWithoutGlitchScreen(
            openCenteredScreen = navController::navigateToCenteredAdjustNothing,
        )

        windowInsetsChatScreen()

        centeredAdjustNothingScreen()
    }
}
