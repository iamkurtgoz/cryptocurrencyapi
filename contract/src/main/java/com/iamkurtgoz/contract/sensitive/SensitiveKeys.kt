package com.iamkurtgoz.contract.sensitive

object SensitiveKeys {

    init {
        System.loadLibrary("native-lib")
    }

    external fun baseAddress(): String
}
