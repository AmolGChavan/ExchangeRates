package com.paypay.exchangerates.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.paypay.exchangerates.data.manager.PreferenceKeys
import com.paypay.exchangerates.domain.manager.Logger
import com.paypay.exchangerates.domain.manager.TimeProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test


class APISyncTimeRepositoryImplTest {

    private val timeProvider: TimeProvider = mockk()
    private val dataStore: DataStore<Preferences> = mockk()
    private val logger: Logger = mockk()
    private val repository = APISyncTimeRepositoryImpl(timeProvider, dataStore, logger)


    @Test
    fun `test readSyncTime returns saved time`() = runTest {
        val expectedTime = 1638316800000L
        val preferences: Preferences = mockk()


        coEvery { dataStore.data } returns flowOf(preferences)
        every { preferences[PreferenceKeys.DATA_SYNC_TIME] } returns expectedTime

        val result = repository.readSyncTime()

        assertEquals(expectedTime, result)
    }

    @Test
    fun `test readSyncTime returns null when no time is saved`() = runTest {
        val preferences: Preferences = mockk()

        coEvery { dataStore.data } returns flowOf(preferences)
        every { preferences[PreferenceKeys.DATA_SYNC_TIME] } returns null

        val result = repository.readSyncTime()

        assertNull(result)
    }
}