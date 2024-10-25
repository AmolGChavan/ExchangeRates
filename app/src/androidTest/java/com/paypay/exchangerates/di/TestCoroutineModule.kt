package com.paypay.exchangerates.di

import com.paypay.exchangerates.domain.manager.Logger
import com.paypay.exchangerates.utils.GlobleCoroutineExceptionHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.test.StandardTestDispatcher
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [CoroutineModule::class]
)
object TestCoroutineModule {


    @Provides
    @CoroutineModule.IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher {
        return StandardTestDispatcher() // Provides the IO dispatcher
    }

    @Provides
    @CoroutineModule.MainDispatcher
    fun provideMainDispatcher(): CoroutineDispatcher {
        return StandardTestDispatcher() // Provides the Main dispatcher
    }

    @Provides
    @CoroutineModule.DefaultDispatcher
    fun provideDefaultDispatcher(): CoroutineDispatcher {
        return StandardTestDispatcher() // Provides the Default dispatcher
    }

    @Provides
    @Singleton
    fun provideCoroutineExceptionHandler(logger: Logger): CoroutineExceptionHandler {
        return GlobleCoroutineExceptionHandler(logger)
    }
}