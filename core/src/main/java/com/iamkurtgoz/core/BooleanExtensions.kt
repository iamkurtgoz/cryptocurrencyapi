package com.iamkurtgoz.core

val Boolean?.orFalse
    get() = this ?: false
