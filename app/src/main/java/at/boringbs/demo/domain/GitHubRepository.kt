package at.boringbs.demo.domain

import at.boringbs.demo.data.model.GithubSearchItem
import at.boringbs.demo.data.model.GithubUser
import at.boringbs.demo.network.retrofit.GitHubService
import javax.inject.Inject

interface GitHubRepository {
    suspend fun getUserItems(user: String): List<GithubSearchItem>
    suspend fun getUser(user: String): GithubUser
}

class GitHubRepositoryImpl @Inject constructor(
    private val service: GitHubService
): GitHubRepository {

    override suspend fun getUserItems(user: String): List<GithubSearchItem> {
        val response = service.searchUsers(user, "1", "24")
        return response.items
    }

    override suspend fun getUser(user: String): GithubUser {
        return service.getUser(user)
    }
}
