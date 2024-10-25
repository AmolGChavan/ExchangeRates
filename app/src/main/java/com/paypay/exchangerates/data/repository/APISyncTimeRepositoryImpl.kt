package com.paypay.exchangerates.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.paypay.exchangerates.data.manager.PreferenceKeys
import com.paypay.exchangerates.domain.manager.Logger
import com.paypay.exchangerates.domain.manager.TimeProvider
import com.paypay.exchangerates.domain.repository.APISyncTimeRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class APISyncTimeRepositoryImpl @Inject constructor(
    private val timeProvider: TimeProvider,
    private val dataStore: DataStore<Preferences>,
    private val logger: Logger
) : APISyncTimeRepository {

    override suspend fun saveSyncTime() {
        val syncTime = timeProvider.currentTimeMillis()
        logger.i("sync Time: $syncTime")
        dataStore.edit { settings ->
            settings[PreferenceKeys.DATA_SYNC_TIME] = syncTime
        }
    }

    override suspend fun readSyncTime(): Long? {
        return dataStore.data.first()[PreferenceKeys.DATA_SYNC_TIME]
    }
}