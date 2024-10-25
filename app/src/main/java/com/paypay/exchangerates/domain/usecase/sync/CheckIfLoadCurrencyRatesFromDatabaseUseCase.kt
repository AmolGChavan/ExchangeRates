package com.paypay.exchangerates.domain.usecase.sync

import com.paypay.exchangerates.domain.repository.DatabaseOrAPISyncUtilRepository
import javax.inject.Inject

class CheckIfLoadCurrencyRatesFromDatabaseUseCase @Inject constructor(private val localOrAPISyncRepository: DatabaseOrAPISyncUtilRepository) {
    suspend operator fun invoke(apiCallFrequencyInMinute: Int): Boolean {
        return localOrAPISyncRepository.checkIfLoadCurrencyRatesFromLocal(apiCallFrequencyInMinute)
    }
}