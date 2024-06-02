package at.boringbs.demo.viewmodel.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import at.boringbs.demo.domain.GetUserUseCase
import at.boringbs.demo.domain.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {

    private var job: Job? = null
    private val _state = MutableStateFlow<DetailScreenState>(DetailScreenState.Loading)
    val state: StateFlow<DetailScreenState> = _state

    fun loadUser(login: String) {
        job?.cancel()
        _state.value = DetailScreenState.Loading
        job = viewModelScope.launch {
            when (val loaded = getUserUseCase(login)) {
                is UserData.Fail -> {
                    _state.value = DetailScreenState.Error("DATA LOAD FAILED")
                }
                is UserData.Success -> {
                    _state.value = DetailScreenState.Data(user = loaded.data)
                }
            }
        }
    }
}
