package com.paypay.exchangerates.di

import com.paypay.exchangerates.FakeExchangeRatesApi
import com.paypay.exchangerates.data.remote.ExchangeRatesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [AppModule::class] // replace with your actual production module
)
object TestAppModule {

    @Provides
    fun provideExchangeRatesApi(): ExchangeRatesApi = FakeExchangeRatesApi()

}