package com.paypay.exchangerates.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class CurrencyRate(
    @PrimaryKey val currency: String,
    val currencyRate: Double
) : Parcelable




