package com.example.kraken.viewModel

import com.example.kraken.model.Joke
import com.example.kraken.useCase.IFetchJokesUseCase
import com.example.kraken.utils.Resource
import com.example.kraken.utils.Status
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var fetchJokesUseCase: IFetchJokesUseCase

    private lateinit var mainViewModel: MainViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mainViewModel = MainViewModel(fetchJokesUseCase)
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun fetchJokesSuccess() = runBlocking {
        //given
        val jokes = listOf(
            Joke(
                "id1",
                "joke de papa",
                "connais-tu la joke de la cravate?",
                "est longue pis est plate"
            )
        )
        Mockito.`when`(fetchJokesUseCase.execute())
            .thenReturn(Resource(Status.SUCCESS, jokes, null))

        //when
        val result = mainViewModel.fetchUsers()

        //then
        assert(result.value?.data?.status == Status.SUCCESS)
        assert(result.value?.data?.data == jokes)
    }

    @Test
    fun fetchJokesFailure() = runBlocking {
        //given
        val jokes = null
        Mockito.`when`(fetchJokesUseCase.execute())
            .thenReturn(Resource(Status.ERROR, jokes, "An error has occurred"))

        //when
        val result = mainViewModel.fetchUsers()

        //then
        assert(result.value?.data?.status == Status.ERROR)
        assert(result.value?.data?.data == jokes)
        assert(result.value?.data?.message == "An error has occurred")
    }

}