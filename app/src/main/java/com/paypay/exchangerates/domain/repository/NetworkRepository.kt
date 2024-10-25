package com.paypay.exchangerates.domain.repository

interface NetworkRepository{
    suspend fun isNetworkAvailable():Boolean
}