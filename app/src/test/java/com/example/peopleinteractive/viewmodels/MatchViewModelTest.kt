package com.example.peopleinteractive.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.peopleinteractive.models.Match
import com.example.peopleinteractive.repository.Repository
import com.example.peopleinteractive.util.ApiStatus
import kotlinx.coroutines.*
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MatchViewModelTest {

    private lateinit var viewModel: MatchViewModel

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private var repository: Repository? = null

    private lateinit var match:Match

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        viewModel = MatchViewModel(repository!!)
    }

    @Test
    fun getMatches() {
        coroutineTestRule.runBlockingTest {
            coroutineTestRule.pauseDispatcher()
            viewModel = MatchViewModel(repository!!)
            viewModel.getMatches()
            coroutineTestRule.resumeDispatcher()
            assert(viewModel.status.value == ApiStatus.DONE)
        }
    }

    @Test
    fun `updateMatch$app_debug`() {
        match = Match("123", "", isAccepted = true, isDeclined = false)
        coroutineTestRule.runBlockingTest {
            viewModel.updateMatch(match)
        }
    }
}