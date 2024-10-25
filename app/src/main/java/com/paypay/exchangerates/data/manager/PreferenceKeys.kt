package com.paypay.exchangerates.data.manager

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import com.paypay.exchangerates.utils.Constants


internal object PreferenceKeys {
    val DATA_SYNC_TIME = longPreferencesKey(Constants.DATA_SYNC_TIME)
    val APP_ENTRY = booleanPreferencesKey(Constants.APP_ENTRY)
}
