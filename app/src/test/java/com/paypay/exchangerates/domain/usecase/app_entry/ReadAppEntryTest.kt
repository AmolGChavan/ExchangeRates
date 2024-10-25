package com.paypay.exchangerates.domain.usecase.app_entry


import com.paypay.exchangerates.domain.repository.LocalAppMangerRepository
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test


class ReadAppEntryTest {

    private lateinit var mockLocalAppMangerRepository: LocalAppMangerRepository
    private lateinit var readAppEntry: ReadAppEntry

    @Before
    fun setUp() {

        mockLocalAppMangerRepository = mockk()


        readAppEntry = ReadAppEntry(mockLocalAppMangerRepository)
    }

    @Test
    fun `invoke should return flow from localAppMangerRepository`() = runTest {

        val expectedFlow = flow { emit(true) }


        every { mockLocalAppMangerRepository.readAppEntry() } returns expectedFlow

        val resultFlow = readAppEntry()
        val emittedValue = resultFlow.toList()


        assertEquals(listOf(true), emittedValue)
    }

    @Test
    fun `invoke should return flow with false from localAppMangerRepository`() = runTest {

        val expectedFlow = flow { emit(false) }


        every { mockLocalAppMangerRepository.readAppEntry() } returns expectedFlow


        val resultFlow = readAppEntry()
        val emittedValue = resultFlow.toList()


        assertEquals(listOf(false), emittedValue)
    }
}