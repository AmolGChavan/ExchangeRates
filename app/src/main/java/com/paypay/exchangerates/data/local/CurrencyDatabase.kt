package com.paypay.exchangerates.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.paypay.exchangerates.domain.CurrencyRate

@Database(entities = [CurrencyRate::class],version = 1, exportSchema = false)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract val exchangeRatesDao: ExchangeRatesDao
}