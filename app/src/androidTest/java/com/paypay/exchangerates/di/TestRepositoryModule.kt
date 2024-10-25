package com.paypay.exchangerates.di

import com.paypay.exchangerates.domain.repository.APISyncTimeRepository
import com.paypay.exchangerates.domain.repository.DatabaseOrAPISyncUtilRepository
import com.paypay.exchangerates.domain.repository.GetCurrencyExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.LocalAppMangerRepository
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
object TestRepositoryModule {

    @Provides
    fun provideLocalExchangeRatesRepository(): LocalExchangeRatesRepository = mockk<LocalExchangeRatesRepository>()

    @Provides
    fun provideGetExchangeRatesRepository(): GetExchangeRatesRepository = mockk()

    @Provides
    fun provideAPISyncTimeRepository(): APISyncTimeRepository = mockk()

    @Provides
    fun provideDatabaseOrAPISyncUtilRepository(): DatabaseOrAPISyncUtilRepository = mockk()

    @Provides
    fun provideLocalAppMangerRepository(): LocalAppMangerRepository = mockk()

    @Provides
    fun provideGetCurrencyExchangeRatesRepository(): GetCurrencyExchangeRatesRepository = mockk()

}