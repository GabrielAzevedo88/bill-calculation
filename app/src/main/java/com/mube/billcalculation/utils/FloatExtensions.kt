package com.mube.billcalculation.utils

import java.text.NumberFormat
import java.util.Locale

fun Float.toCurrency(): String {
    val defaultFormat: NumberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA)
    return defaultFormat.format(this)
}

infix fun Float.percentOf(value: Float): Float = if (this == 0f) 0f else value * (this / 100)
infix fun Float.amountOf(value: Float): Float = if (value <= this) 0f else this
