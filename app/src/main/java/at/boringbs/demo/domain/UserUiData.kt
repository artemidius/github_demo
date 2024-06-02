package at.boringbs.demo.domain

import androidx.compose.runtime.Stable
import at.boringbs.demo.data.model.GithubUser

@Stable
data class UserUiData(
    val name: String?,
    val login: String?,
    val icon: String,
    val reps: String,
    val bio: String?,
    val company: String?,
    val hireable: String?,
)

fun GithubUser.toUiData(): UserUiData = UserUiData(
    name =name ?: "",
    login = login,
    icon = avatarUrl ?: "",
    reps = publicRepos.toString(),
    bio = bio,
    company = company,
    hireable = hireable?.let { it.toString() }
)