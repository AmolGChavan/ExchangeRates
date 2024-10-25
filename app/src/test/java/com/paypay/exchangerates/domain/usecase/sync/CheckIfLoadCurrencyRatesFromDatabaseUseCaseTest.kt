package com.paypay.exchangerates.domain.usecase.sync

import com.paypay.exchangerates.domain.repository.DatabaseOrAPISyncUtilRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test


class CheckIfLoadCurrencyRatesFromDatabaseUseCaseTest{

    private lateinit var mockDatabaseOrAPISyncUtilRepository: DatabaseOrAPISyncUtilRepository
    private lateinit var checkIfLoadCurrencyRatesFromDatabaseUseCase: CheckIfLoadCurrencyRatesFromDatabaseUseCase

    @Before
    fun setUp() {
        mockDatabaseOrAPISyncUtilRepository = mockk()
        checkIfLoadCurrencyRatesFromDatabaseUseCase = CheckIfLoadCurrencyRatesFromDatabaseUseCase(mockDatabaseOrAPISyncUtilRepository)
    }

    @Test
    fun `invoke should return true when checkIfLoadCurrencyRatesFromLocal returns true`() = runTest {
        val apiCallFrequencyInMinute = 10
        coEvery { mockDatabaseOrAPISyncUtilRepository.checkIfLoadCurrencyRatesFromLocal(apiCallFrequencyInMinute) } returns true
        val result = checkIfLoadCurrencyRatesFromDatabaseUseCase(apiCallFrequencyInMinute)
        assertEquals(true, result)
        coVerify { mockDatabaseOrAPISyncUtilRepository.checkIfLoadCurrencyRatesFromLocal(apiCallFrequencyInMinute) }
    }

    @Test
    fun `invoke should return false when checkIfLoadCurrencyRatesFromLocal returns false`() = runTest {
        val apiCallFrequencyInMinute = 10
        coEvery { mockDatabaseOrAPISyncUtilRepository.checkIfLoadCurrencyRatesFromLocal(apiCallFrequencyInMinute) } returns false
        val result = checkIfLoadCurrencyRatesFromDatabaseUseCase(apiCallFrequencyInMinute)
        assertEquals(false, result)
        coVerify { mockDatabaseOrAPISyncUtilRepository.checkIfLoadCurrencyRatesFromLocal(apiCallFrequencyInMinute) }
    }
}