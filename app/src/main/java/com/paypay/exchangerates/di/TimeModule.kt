package com.paypay.exchangerates.di

import com.paypay.exchangerates.data.utils.DefaultTimeProvider
import com.paypay.exchangerates.domain.manager.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TimeModule {
    @Provides
    fun provideTimeProvider(): TimeProvider {
        return DefaultTimeProvider()
    }
}