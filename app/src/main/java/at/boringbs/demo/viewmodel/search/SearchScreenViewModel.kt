package at.boringbs.demo.viewmodel.search

import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.boringbs.demo.domain.GetUsersListUseCase
import at.boringbs.demo.domain.UsersListData
import at.boringbs.demo.domain.UsersListItemUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    val getUsersListUseCase: GetUsersListUseCase
) : ViewModel() {
    private var job: Job? = null
    private val _state = MutableStateFlow<SearchScreenState>(SearchScreenState.Data.Init)
    val state: StateFlow<SearchScreenState> = _state

    fun onValueChange(value: String) {
        job?.cancel()
        val currentUsers = (_state.value as? SearchScreenState.Data)?.users
            ?: emptyList<UsersListItemUiData>().toImmutableList()

        _state.value = SearchScreenState.Data(
            users = currentUsers,
            searchQuery = value,
            networkOn = _state.value.networkOn,
            isLoading = value.length >= 2,
            showDetail = null
        )
        if (value.length >= 2) {
            job = viewModelScope.launch {
                delay(1000) //throttles input
                when (val list = getUsersListUseCase(value)) {
                    is UsersListData.Success -> {
                        _state.value = (_state.value as SearchScreenState.Data).copy(
                            searchQuery = value,
                            isLoading = false,
                            users = list.data
                        )
                    }

                    is UsersListData.Fail -> {
                        val errorText = when (list.e) {
                            is HttpException -> {
                                "Network Error. CODE: ${list.e.code()}.\nMESSAGE: ${list.e.message}"
                            }
                            is UnknownHostException -> {
                                "Network issue. Please, check your connection"
                            }
                            else -> "Sorry, something went wrong.\n${list.e.message}"
                        }
                        _state.value = SearchScreenState.Error(
                            message = errorText,
                            searchQuery = _state.value.searchQuery,
                            networkOn = _state.value.networkOn
                        )
                    }
                }
            }
        }
    }

    fun onClear() {
        job?.cancel()
        _state.value = SearchScreenState.Data(
            users = emptyList<UsersListItemUiData>().toImmutableList(),
            searchQuery = "",
            networkOn = _state.value.networkOn,
            isLoading = false,
            showDetail = null
        )
    }

    fun onClick(pos: Int) {
        (_state.value as? SearchScreenState.Data)?.let {
            _state.value = it.copy(showDetail = it.users[pos])
        }
    }

    fun onReturn() {
        (_state.value as? SearchScreenState.Data)?.let {
            _state.value = it.copy(showDetail = null)
        }
    }

    val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            setNetworkStatus(true)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            setNetworkStatus(false)
        }

        fun setNetworkStatus(status: Boolean) {
            when (_state.value) {
                is SearchScreenState.Data -> {
                    _state.value = (_state.value as SearchScreenState.Data).copy(networkOn = status)
                }

                is SearchScreenState.Error -> {
                    _state.value =
                        (_state.value as SearchScreenState.Error).copy(networkOn = status)
                }
            }
        }
    }
}
