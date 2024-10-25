package com.paypay.exchangerates.di

import com.paypay.exchangerates.data.repository.APISyncTimeRepositoryImpl
import com.paypay.exchangerates.data.repository.DatabaseOrAPISyncUtilRepositoryImpl
import com.paypay.exchangerates.data.repository.GetCurrencyExchangeRatesRepositoryImpl
import com.paypay.exchangerates.data.repository.GetExchangeRatesRepositoryImpl
import com.paypay.exchangerates.data.repository.LocalAppMangerRepositoryImpl
import com.paypay.exchangerates.data.repository.LocalExchangeRatesRepositoryImpl
import com.paypay.exchangerates.data.repository.NetworkRepositoryImpl
import com.paypay.exchangerates.domain.repository.APISyncTimeRepository
import com.paypay.exchangerates.domain.repository.DatabaseOrAPISyncUtilRepository
import com.paypay.exchangerates.domain.repository.GetCurrencyExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.GetExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.LocalAppMangerRepository
import com.paypay.exchangerates.domain.repository.LocalExchangeRatesRepository
import com.paypay.exchangerates.domain.repository.NetworkRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindExchangeRatesRepository(exchangeRatesRepositoryImpl: GetExchangeRatesRepositoryImpl) : GetExchangeRatesRepository

    @Binds
    @Singleton
    abstract fun bindLocalExchangeRatesRepository(localExchangeRatesRepositoryImpl: LocalExchangeRatesRepositoryImpl) : LocalExchangeRatesRepository


    @Binds
    @Singleton
    abstract fun bindLocalOrAPISyncRepository(localOrAPISyncRepositoryImpl: DatabaseOrAPISyncUtilRepositoryImpl) : DatabaseOrAPISyncUtilRepository

    @Binds
    @Singleton
    abstract fun bindAPISyncTimeRepositoryImpl(aPISyncTimeRepositoryImpl: APISyncTimeRepositoryImpl) : APISyncTimeRepository

    @Binds
    @Singleton
    abstract fun bindNetworkRepository(networkRepositoryImpl: NetworkRepositoryImpl) : NetworkRepository


    @Binds
    @Singleton
    abstract fun bindLocalAppMangerRepository(localAppMangerRepository: LocalAppMangerRepositoryImpl): LocalAppMangerRepository


    @Binds
    @Singleton
    abstract fun bindGetCurrencyExchangeRatesRepository(getCurrencyExchangeRatesRepositoryImpl: GetCurrencyExchangeRatesRepositoryImpl): GetCurrencyExchangeRatesRepository

}