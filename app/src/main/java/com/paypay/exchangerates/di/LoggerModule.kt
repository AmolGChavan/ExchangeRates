package com.paypay.exchangerates.di

import com.paypay.exchangerates.data.manager.TimberLogger
import com.paypay.exchangerates.domain.manager.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LoggerModule {
    @Provides
    fun provideLogger(): Logger {
        return TimberLogger()
    }
}