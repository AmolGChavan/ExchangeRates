package com.paypay.exchangerates.domain.repository

interface APISyncTimeRepository {

    suspend fun saveSyncTime()

    suspend fun readSyncTime(): Long?
}