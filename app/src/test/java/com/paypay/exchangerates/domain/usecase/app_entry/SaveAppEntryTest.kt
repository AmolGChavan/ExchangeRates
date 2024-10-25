package com.paypay.exchangerates.domain.usecase.app_entry

import com.paypay.exchangerates.domain.repository.LocalAppMangerRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SaveAppEntryTest {
    private lateinit var mockLocalAppMangerRepository: LocalAppMangerRepository
    private lateinit var saveAppEntry: SaveAppEntry

    @Before
    fun setUp() {
        mockLocalAppMangerRepository = mockk()

        saveAppEntry = SaveAppEntry(mockLocalAppMangerRepository)
    }
    @Test
    fun `invoke should call saveAppEntry on localAppMangerRepository`() = runTest {

        coEvery { mockLocalAppMangerRepository.saveAppEntry() } returns Unit // No return value needed

        saveAppEntry()

        coVerify { mockLocalAppMangerRepository.saveAppEntry() }
    }


}