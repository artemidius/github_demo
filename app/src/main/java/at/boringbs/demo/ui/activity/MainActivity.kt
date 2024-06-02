package at.boringbs.demo.ui.activity

import android.net.ConnectivityManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import at.boringbs.demo.ui.navigation.AppNavHost
import at.boringbs.demo.ui.theme.GHBDemoTheme
import at.boringbs.demo.viewmodel.detail.DetailScreenViewModel
import at.boringbs.demo.viewmodel.search.SearchScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val searchScreenViewModel: SearchScreenViewModel by viewModels()
        val detailScreenViewModel: DetailScreenViewModel by viewModels()
        searchScreenViewModel.networkCallback.registerNetworkCallback()
        setContent {
            GHBDemoTheme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavHost(
                        navController = navController,
                        searchScreenViewModel = searchScreenViewModel,
                        detailScreenViewModel = detailScreenViewModel
                    )
                }
            }
        }
    }

    private fun ConnectivityManager.NetworkCallback.registerNetworkCallback() {
        val connectivityManager =
            getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(this)
    }
}
