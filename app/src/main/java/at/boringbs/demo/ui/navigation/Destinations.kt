package at.boringbs.demo.ui.navigation

object Search : AppNavigationDestination {
    override val route = "search"
}

object Details : AppNavigationDestination {
    private const val routeBase = "details/"
    override val route = "$routeBase{login}"
    fun routeWithLogin(login: String) = routeBase+login
}