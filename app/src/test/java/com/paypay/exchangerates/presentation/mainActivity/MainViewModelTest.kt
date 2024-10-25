package com.paypay.exchangerates.presentation.mainActivity

import com.paypay.exchangerates.domain.usecase.app_entry.ReadAppEntry
import com.paypay.exchangerates.presentation.navgraph.Route
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class MainViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private lateinit var viewModel: MainViewModel
    private val readAppEntry: ReadAppEntry = mockk(relaxed = true)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        clearAllMocks()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should start from home screen when ReadAppEntry returns true`() = runTest {

        coEvery { readAppEntry() } returns flowOf(true)
        viewModel = MainViewModel(readAppEntry) //re-initialize ViewModel

        advanceTimeBy(1000)

        assert(viewModel.startDestination.value == Route.HomeScreen.route)
        assert(!viewModel.splashCondition.value)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `should start from app start navigation when ReadAppEntry returns false`() = runTest {

        coEvery { readAppEntry() } returns flowOf(false)
        viewModel = MainViewModel(readAppEntry)

        advanceTimeBy(1000)

        assert(viewModel.startDestination.value == Route.AppStartNavigation.route)
        assert(!viewModel.splashCondition.value)
    }
}