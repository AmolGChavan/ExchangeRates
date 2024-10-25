package com.paypay.exchangerates.domain.usecase.network

import com.paypay.exchangerates.domain.repository.NetworkRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class CheckInternetConnectionStatusTest {

    private lateinit var mockNetworkRepository: NetworkRepository
    private lateinit var checkInternetConnectionStatus: CheckInternetConnectionStatus

    @Before
    fun setUp() {
        mockNetworkRepository = mockk()

        checkInternetConnectionStatus = CheckInternetConnectionStatus(mockNetworkRepository)
    }

    @Test
    fun `invoke should return true when network is available`() = runTest {
        coEvery { mockNetworkRepository.isNetworkAvailable() } returns true

        val result = checkInternetConnectionStatus()

        assertEquals(true, result)
        coVerify { mockNetworkRepository.isNetworkAvailable() }
    }

    @Test
    fun `invoke should return false when network is not available`() = runTest {
        coEvery { mockNetworkRepository.isNetworkAvailable() } returns false

        val result = checkInternetConnectionStatus()

        assertEquals(false, result)
        coVerify { mockNetworkRepository.isNetworkAvailable() }
    }

}