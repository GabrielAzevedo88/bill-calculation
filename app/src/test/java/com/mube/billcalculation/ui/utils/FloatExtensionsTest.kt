package com.mube.billcalculation.ui.utils

import com.mube.billcalculation.utils.toCurrency
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class FloatExtensionsTest {

    @Test
    fun `test formatter with decimal value`() {
        val expected = "$15.98"
        val actual = 15.98f.toCurrency()

        assertEquals(expected, actual)
    }

    @Test
    fun `test formatter without decimal value`() {
        val expected = "$15.00"
        val actual = 15f.toCurrency()

        assertEquals(expected, actual)
    }

    @Test
    fun `test formatter without value`() {
        val expected = "$0.00"
        val actual = 0f.toCurrency()

        assertEquals(expected, actual)
    }

}