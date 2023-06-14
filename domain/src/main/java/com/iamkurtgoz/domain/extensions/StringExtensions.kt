package com.iamkurtgoz.domain.extensions

import java.text.NumberFormat
import java.util.Locale

val String?.formatDecimal
    get() = run {
        val numberFormat = NumberFormat.getNumberInstance(Locale("tr", "TR"))
        numberFormat.maximumFractionDigits = 2
        "${numberFormat.format(this?.toDoubleOrNull() ?: 0.0)}₺"
    }