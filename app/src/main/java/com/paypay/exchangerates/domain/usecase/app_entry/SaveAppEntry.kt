package com.paypay.exchangerates.domain.usecase.app_entry

import com.paypay.exchangerates.domain.repository.LocalAppMangerRepository
import javax.inject.Inject

class SaveAppEntry @Inject constructor(
    private val localAppMangerRepository: LocalAppMangerRepository
) {
    suspend operator fun invoke() {
        localAppMangerRepository.saveAppEntry()
    }
}