package at.boringbs.demo.domain

import javax.inject.Inject


interface GetUserUseCase {
    suspend operator fun invoke(login: String): UserData
}

class GetUserUseCaseImpl @Inject constructor(private val repository: GitHubRepository) : GetUserUseCase {
    override suspend fun invoke(login: String): UserData {
        return try {
            UserData.Success(
                repository.getUser(login).toUiData()
            )
        } catch (e: Throwable) {
            UserData.Fail(e)
        }
    }
}
