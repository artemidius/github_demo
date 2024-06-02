package at.boringbs.demo.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import at.boringbs.demo.viewmodel.search.SearchScreenState
import at.boringbs.demo.viewmodel.search.SearchScreenViewModel

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchScreenViewModel,
    navigateToDetails: (login: String) -> Unit
) {
    val state = viewModel.state.collectAsState().value
    val isLoading = (state as? SearchScreenState.Data)?.isLoading ?: false
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(24.dp))
        SearchBar(
            value = state.searchQuery,
            isLoading = isLoading,
            onValueChange = viewModel::onValueChange,
            onClear = viewModel::onClear
        )
        Column(
            Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            when (state) {
                is SearchScreenState.Data -> {
                    LaunchedEffect(state) {
                        state.showDetail?.login?.let {
                            navigateToDetails(it)
                        }
                    }
                    Spacer(modifier = Modifier.height(18.dp))
                    if (state.users.isNotEmpty()) {
                        HorizontalDivider(color = Color.Gray, thickness = 1.dp)
                        UsersList(
                            users = state.users,
                            onClick = {
                                keyboardController?.hide()
                                viewModel.onClick(it)
                            }
                        )
                    }
                }

                is SearchScreenState.Error -> {
                    Box(
                        contentAlignment = Alignment.TopCenter,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 24.dp, vertical = 64.dp)
                    ) {
                        Text(
                            text = state.message,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
        }
        NetworkAlert(networkOn = state.networkOn)
    }
}
