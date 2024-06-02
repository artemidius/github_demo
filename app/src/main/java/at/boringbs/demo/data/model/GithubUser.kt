package at.boringbs.demo.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubUser(
    @SerialName("avatar_url")
    val avatarUrl: String?,
    val bio: String?,
    val blog: String?,
    val company: String?,
    @SerialName("created_at")
    val createdAt: String?,
    val email: String?,
    @SerialName("events_url")
    val eventsUrl: String?,
    val followers: Int,
    @SerialName("followers_url")
    val followersUrl: String?,
    val following: Int,
    @SerialName("following_url")
    val followingUrl: String?,
    @SerialName("gists_url")
    val gistsUrl: String?,
    val hireable: Boolean?,
    @SerialName("gravatar_id")
    val gravatarId: String?,
    @SerialName("html_url")
    val htmlUrl: String?,
    val id: Int,
    val location: String?,
    val login: String?,
    val name: String?,
    @SerialName("node_id")
    val nodeId: String?,
    @SerialName("organizations_url")
    val organizationsUrl: String?,
    @SerialName("public_gists")
    val publicGists: Int,
    @SerialName("public_repos")
    val publicRepos: Int,
    @SerialName("received_events_url")
    val receivedEventsUrl: String?,
    @SerialName("repos_url")
    val reposUrl: String?,
    @SerialName("site_admin")
    val siteAdmin: Boolean,
    @SerialName("starred_url")
    val starredUrl: String?,
    @SerialName("subscriptions_url")
    val subscriptionsUrl: String?,
    @SerialName("twitter_username")
    val twitterUsername: String?,
    val type: String?,
    @SerialName("updated_at")
    val updatedAt: String?,
    val url: String?
)