package com.paypay.exchangerates.utils

import com.paypay.exchangerates.domain.manager.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class GlobleCoroutineExceptionHandler @Inject constructor(val logger: Logger) : CoroutineExceptionHandler {
    override val key = CoroutineExceptionHandler.Key

    override fun handleException(context: CoroutineContext, exception: Throwable) {
        logger.e("EXE_RATE_EXCEPTION:${exception.message}")
    }
}