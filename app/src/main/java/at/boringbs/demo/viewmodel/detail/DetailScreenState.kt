package at.boringbs.demo.viewmodel.detail

import at.boringbs.demo.domain.UserUiData

sealed interface DetailScreenState {

    data class Data(
        val user: UserUiData,
    ) : DetailScreenState

    data class Error(
        val message: String,
    ) : DetailScreenState

    data object Loading : DetailScreenState
}
