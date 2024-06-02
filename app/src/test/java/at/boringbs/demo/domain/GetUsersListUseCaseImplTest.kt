package at.boringbs.demo.domain

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test


class GetUsersListUseCaseImplTest {

    private val repository: GitHubRepository = mockk()
    private val useCase: GetUsersListUseCase = GetUsersListUseCaseImpl(repository)

    @Test
    fun invokeSuccessful() = runTest {
        coEvery { repository.getUserItems(any()) } returns emptyList()
        val res = useCase("1337")
        assertThat(res, instanceOf(UsersListData.Success::class.java))
    }

    @Test
    fun invokeFail() = runTest {
        coEvery { repository.getUserItems(any()) } throws Throwable("ERROR")
        val res = useCase("42")
        assertThat(res, instanceOf(UsersListData.Fail::class.java))
    }
}
