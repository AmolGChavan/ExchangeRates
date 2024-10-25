package com.paypay.exchangerates.domain.repository

import kotlinx.coroutines.flow.Flow

interface LocalAppMangerRepository {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>

}