package dev.ferrant.tickers.extensions

import org.junit.Assert.assertEquals
import org.junit.Test

class TickerDataExtensionTest {

    @Test
    fun long_can_be_mapped_to_formatted_date() {
        val dateInMilliseconds: Long = 1668525601152
        val dateInString = "15/11/2022"
        val formattedDate = dateInMilliseconds.toDateFormat()
        assertEquals(dateInString, formattedDate)
    }

    @Test
    fun long_can_be_mapped_to_formatted_time() {
        val timeInMilliseconds: Long = 1668525601152
        val timeInString = "16:20:01"
        val formattedDate = timeInMilliseconds.toTimeFormat()
        assertEquals(timeInString, formattedDate)
    }

}
