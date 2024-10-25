package com.paypay.exchangerates.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.paypay.exchangerates.data.manager.PreferenceKeys
import com.paypay.exchangerates.domain.repository.LocalAppMangerRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalAppMangerRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : LocalAppMangerRepository {

    override suspend fun saveAppEntry() {
        dataStore.edit { settings ->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }

    override fun readAppEntry(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }
}


