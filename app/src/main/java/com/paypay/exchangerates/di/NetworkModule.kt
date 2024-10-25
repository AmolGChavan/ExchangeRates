package com.paypay.exchangerates.di

import android.content.Context
import android.net.ConnectivityManager
import com.paypay.exchangerates.data.repository.NetworkRepositoryImpl
import com.paypay.exchangerates.domain.repository.NetworkRepository
import com.paypay.exchangerates.data.utils.network.DefaultNetworkCapabilitiesProvider
import com.paypay.exchangerates.data.utils.network.NetworkCapabilitiesProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkCapabilitiesProvider(connectivityManager: ConnectivityManager): NetworkCapabilitiesProvider {
        return DefaultNetworkCapabilitiesProvider(connectivityManager)
    }



    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}