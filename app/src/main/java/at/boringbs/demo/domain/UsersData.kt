package at.boringbs.demo.domain

import kotlinx.collections.immutable.ImmutableList

sealed interface UsersListData {
    data class Success(val data: ImmutableList<UsersListItemUiData>) : UsersListData
    data class Fail(val e: Throwable) : UsersListData
}


sealed interface UserData {
    data class Success(val data: UserUiData) : UserData
    data class Fail(val e: Throwable) : UserData
}