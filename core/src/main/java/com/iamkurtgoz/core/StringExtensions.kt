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