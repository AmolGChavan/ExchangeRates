package com.paypay.exchangerates.di

import com.paypay.exchangerates.domain.manager.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LoggerModule::class]
)
object TestLoggerModule {

    @Provides
    fun provideLogger(): Logger = mockk(relaxed = true)
}