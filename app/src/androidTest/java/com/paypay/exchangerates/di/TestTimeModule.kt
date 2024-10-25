package com.paypay.exchangerates.di

import com.paypay.exchangerates.domain.manager.TimeProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [TimeModule::class]
)
object TestTimeModule {

    @Provides
    fun provideTimeProvider(): TimeProvider {
        return mockk()
    }
}