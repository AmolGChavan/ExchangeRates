package com.paypay.exchangerates.domain.usecase.app_entry

import com.paypay.exchangerates.domain.repository.LocalAppMangerRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadAppEntry @Inject constructor(
    private val localAppMangerRepository: LocalAppMangerRepository
) {

    operator fun invoke(): Flow<Boolean> {
        return localAppMangerRepository.readAppEntry()
    }

}