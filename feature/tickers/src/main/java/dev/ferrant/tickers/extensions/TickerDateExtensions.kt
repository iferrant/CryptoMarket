package dev.ferrant.tickers.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long.toDateFormat(pattern: String = "dd/MM/yyyy"): String {
    val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
    return simpleDateFormat.format(this)
}

fun Long.toTimeFormat(pattern: String = "HH:mm:ss"): String = toDateFormat(pattern)
