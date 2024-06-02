package at.boringbs.demo.viewmodel.search

import app.cash.turbine.test
import app.cash.turbine.turbineScope
import at.boringbs.demo.domain.GetUsersListUseCase
import at.boringbs.demo.domain.UsersListData
import at.boringbs.demo.domain.UsersListItemUiData
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchScreenViewModelTest {

    private val getUsersListUseCase: GetUsersListUseCase = mockk()
    private val viewModel = SearchScreenViewModel(getUsersListUseCase)

    @Before
    fun setup() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun onValueChange() = runTest {
        coEvery { getUsersListUseCase("mock") } returns
                UsersListData.Success(emptyList<UsersListItemUiData>().toImmutableList())

        turbineScope {
            val turbine = viewModel.state.testIn(backgroundScope)
            assertThat(turbine.awaitItem(), instanceOf(SearchScreenState.Data::class.java))
            viewModel.onValueChange("mock")
            assertEquals(turbine.awaitItem().searchQuery, "mock")
        }
    }

    @Test
    fun onClear() = runTest {
        viewModel.onClear()
        viewModel.state.test {
            assertEquals(awaitItem().searchQuery, "")
        }
    }
}