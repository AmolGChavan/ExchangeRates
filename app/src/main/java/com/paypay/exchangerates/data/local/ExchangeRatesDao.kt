package com.paypay.exchangerates.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.paypay.exchangerates.domain.CurrencyRate

@Dao
interface ExchangeRatesDao {

    @Query("SELECT * FROM CurrencyRate")
    suspend fun getCurrencyRates(): List<CurrencyRate>


    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(currencyRates: List<CurrencyRate>)


}