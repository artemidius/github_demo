package at.boringbs.demo.domain

import androidx.compose.runtime.Stable
import at.boringbs.demo.data.model.GithubSearchItem

@Stable
data class UsersListItemUiData(
    val login: String?,
    val icon: String,
)

fun GithubSearchItem.toUiData(): UsersListItemUiData = UsersListItemUiData(
    login = login,
    icon = avatarUrl,
)