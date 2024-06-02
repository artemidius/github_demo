package at.boringbs.demo.domain

import kotlinx.collections.immutable.toImmutableList
import javax.inject.Inject


interface GetUsersListUseCase {
    suspend operator fun invoke(query: String): UsersListData
}

class GetUsersListUseCaseImpl @Inject constructor(private val repository: GitHubRepository) :
    GetUsersListUseCase {
    override suspend fun invoke(query: String): UsersListData {
        return try {
            UsersListData.Success(
                repository.getUserItems(query).map { it.toUiData() }.toImmutableList()
            )
        } catch (e: Throwable) {
            UsersListData.Fail(e)
        }
    }
}
