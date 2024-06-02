package at.boringbs.demo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import at.boringbs.demo.ui.screen.details.DetailScreen
import at.boringbs.demo.ui.screen.search.SearchScreen
import at.boringbs.demo.viewmodel.detail.DetailScreenViewModel
import at.boringbs.demo.viewmodel.search.SearchScreenViewModel


@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    searchScreenViewModel: SearchScreenViewModel,
    detailScreenViewModel: DetailScreenViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Search.route,
        modifier = modifier,
    ) {
        composable(route = Search.route) {
            SearchScreen(
                viewModel = searchScreenViewModel,
                navigateToDetails = {
                    detailScreenViewModel.loadUser(it)
                    navController.navigate(Details.routeWithLogin(it))
                }
            )
        }

        composable(
            route = Details.route,
            arguments = listOf(navArgument("login") { type = NavType.StringType })
        ) {
            it.arguments?.getString("login")?.let {login ->
                DetailScreen(
                    state = detailScreenViewModel.state.value,
                    onBack = {
                        searchScreenViewModel.onReturn()
                        navController.popBackStack()
                    },
                    login = login
                )
            }?: navController.popBackStack()
        }
    }
}
