package at.boringbs.demo.viewmodel.detail

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import at.boringbs.demo.domain.GetUserUseCase
import at.boringbs.demo.domain.UserData
import at.boringbs.demo.domain.UserUiData
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test


class DetailScreenViewModelTest {

    private val getUserUseCase: GetUserUseCase = mockk()
    private val viewModel = DetailScreenViewModel(getUserUseCase)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun checkInitialState() = runTest {
        turbineScope {
            val turbine = viewModel.state.testIn(backgroundScope)
            assertEquals(turbine.awaitItem(), DetailScreenState.Loading)
        }
    }

    @Test
    fun checkSuccessState() = runTest {
        coEvery { getUserUseCase("mock") } returns UserData.Success(mockedUserUiData)
        viewModel.loadUser("mock")
        viewModel.state.test {
            assertThat(awaitItem(), instanceOf(DetailScreenState.Data::class.java))
        }
    }

    @Test
    fun checkErrorState() = runTest {
        coEvery { getUserUseCase("mock") } returns UserData.Fail(Throwable("error"))
        viewModel.loadUser("mock")
        viewModel.state.test {
            assertThat(awaitItem(), instanceOf(DetailScreenState.Error::class.java))
        }
    }

    private val mockedUserUiData = UserUiData(
        null,
        null,
        "",
        "",
        null,
        null,
        null,
    )
}