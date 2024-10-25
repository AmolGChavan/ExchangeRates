package com.paypay.exchangerates.domain.manager

interface Logger {
    fun d(message: String)
    fun e(message: String)
    fun i(message: String)
    fun w(message: String)
}