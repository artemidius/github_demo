package at.boringbs.demo.domain

import at.boringbs.demo.data.model.GithubUser
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test


class GetUserUseCaseImplTest {

    private val repository: GitHubRepository = mockk()
    private val useCase: GetUserUseCase = GetUserUseCaseImpl(repository)

    @Before
    fun setUp() {
    }

    @Test
    fun invokeSuccess() = runTest {
        coEvery { repository.getUser(any()) } returns mockedUser
        val res = useCase("artemidius")
        assertThat(res, CoreMatchers.instanceOf(UserData.Success::class.java))
    }

    @Test
    fun invokeFail() = runTest {
        coEvery { repository.getUser(any()) } throws Throwable("ERROR")
        val res = useCase("artemidius")
        assertThat(res, CoreMatchers.instanceOf(UserData.Fail::class.java))
    }

    private val mockedUser: GithubUser = GithubUser(
        null,
        null,
        null,
        null,
        null,
        null,
        null,
        0,
        null,
        0,
        null,
        null,
        null,
        null,
        null,
        0,
        null,
        null,
        null,
        null,
        null,
        0,
        0,
        null,
        null,
        false,
        null,
        null,
        null,
        null,
        null,
        null,
    )
}