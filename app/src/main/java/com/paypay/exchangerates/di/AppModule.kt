package com.paypay.exchangerates.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.paypay.exchangerates.data.local.CurrencyDatabase
import com.paypay.exchangerates.data.local.ExchangeRatesDao
import com.paypay.exchangerates.data.remote.ExchangeRatesApi
import com.paypay.exchangerates.utils.Constants.BASE_URL
import com.paypay.exchangerates.utils.Constants.USER_SETTINGS
import com.paypay.exchangerates.data.utils.TimeProvider

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

    @Provides
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    fun provideTimeProvider(): TimeProvider {
        return TimeProvider()
    }

    @Provides
    @Singleton
    fun provideApiInstance(): ExchangeRatesApi {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ExchangeRatesApi::class.java)
    }


    @Provides
    @Singleton
    fun provideCurrencyDatabase(
        application: Application
    ): CurrencyDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = CurrencyDatabase::class.java,
            name = "rates_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeRatesDao(
        currencyDatabase: CurrencyDatabase
    ): ExchangeRatesDao = currencyDatabase.exchangeRatesDao

}