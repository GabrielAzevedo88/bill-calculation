package com.mube.billcalculation.ui.utils

import java.text.NumberFormat

fun Float.toCurrency(): String {
    val defaultFormat: NumberFormat = NumberFormat.getCurrencyInstance()
    return defaultFormat.format(this)
}
