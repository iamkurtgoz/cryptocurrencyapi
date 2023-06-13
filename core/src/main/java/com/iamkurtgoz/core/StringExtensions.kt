package com.iamkurtgoz.core

import java.text.NumberFormat
import java.util.Locale

val String.Companion.Empty
    get() = ""

val String.Companion.Brackets
    get() = "("

val String.Companion.ReverseBrackets
    get() = ")"

val String?.orEmpty
    get() = this ?: String.Empty

val String?.formatDecimal
    get() = run {
        val numberFormat = NumberFormat.getNumberInstance(Locale("tr", "TR"))
        numberFormat.maximumFractionDigits = 2
        "${numberFormat.format(this?.toDoubleOrNull() ?: 0.0)}₺"
    }