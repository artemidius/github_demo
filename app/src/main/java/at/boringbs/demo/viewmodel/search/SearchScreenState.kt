package at.boringbs.demo.viewmodel.search

import at.boringbs.demo.domain.UsersListItemUiData
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

sealed interface SearchScreenState {
    val searchQuery: String
    val networkOn: Boolean

    data class Data(
        val users: ImmutableList<UsersListItemUiData>,
        override val searchQuery: String,
        override val networkOn: Boolean,
        val isLoading: Boolean,
        val showDetail: UsersListItemUiData?
    ) : SearchScreenState {
        companion object {
            val Init = Data(
                users = emptyList<UsersListItemUiData>().toImmutableList(),
                searchQuery = "",
                isLoading = false,
                networkOn = true,
                showDetail = null
            )
        }
    }

    data class Error(
        val message: String,
        override val searchQuery: String,
        override val networkOn: Boolean
    ) : SearchScreenState

}