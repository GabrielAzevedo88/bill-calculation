package com.mube.billcalculation.domain.usecases

import com.mube.discounts.domain.models.Discount
import com.mube.discounts.domain.repository.DiscountRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GetDiscountsValueTest {

    private val mockDiscountRepository: DiscountRepository = mockk {
        coEvery { this@mockk.getAll() } returns DISCOUNTS
    }

    private val getDiscountsValue = GetDiscountsValue(
        discountRepository = mockDiscountRepository
    )

    @Test
    fun `get discount by dollar`() = runTest {
        val expected  = 13f
        val actual = getDiscountsValue(total = TOTAL, selectedDiscountIds = listOf(DOLLAR_ID, OTHER_DOLLAR_ID))

        assertEquals(expected, actual)
    }

    @Test
    fun `get discount by dollar when total is less`() = runTest {
        val expected  = 0f
        val actual = getDiscountsValue(total = 2f, selectedDiscountIds = listOf(DOLLAR_ID, OTHER_DOLLAR_ID))

        assertEquals(expected, actual)
    }

    @Test
    fun `get discount by percentage`() = runTest {
        val expected  = 0f
        val actual = getDiscountsValue(total = 2f, selectedDiscountIds = listOf(PERCENTAGE_ID, OTHER_PERCENTAGE_ID))

        assertEquals(expected, actual)
    }

    @Test
    fun `get discount by percentage and dollar`() = runTest {
        val expected  = 48f
        val actual = getDiscountsValue(total = TOTAL, selectedDiscountIds = DISCOUNTS_IDS)

        assertEquals(expected, actual)
    }

    @Test
    fun `get discount when total is zero`() = runTest {
        val expected  = 0f
        val actual = getDiscountsValue(total = 0f, selectedDiscountIds = DISCOUNTS_IDS)

        assertEquals(expected, actual)
    }

    @Test
    fun `get discount when is no selected discounts`() = runTest {
        val expected  = 0f
        val actual = getDiscountsValue(total = TOTAL, selectedDiscountIds = listOf())

        assertEquals(expected, actual)
    }

    private companion object {
        const val DOLLAR_ID = 100
        const val OTHER_DOLLAR_ID = 200
        const val PERCENTAGE_ID = 300
        const val OTHER_PERCENTAGE_ID = 400

        const val TOTAL = 100f

        val DOLLAR = Discount.Dollar(
            id = DOLLAR_ID,
            name = "discount",
            amount = 5f
        )

        val OTHER_DOLLAR = Discount.Dollar(
            id = OTHER_DOLLAR_ID,
            name = "discount",
            amount = 8f
        )

        val PERCENTAGE = Discount.Dollar(
            id = PERCENTAGE_ID,
            name = "discount",
            amount = 20f
        )

        val OTHER_PERCENTAGE = Discount.Dollar(
            id = OTHER_PERCENTAGE_ID,
            name = "discount",
            amount = 15f
        )

        val DISCOUNTS = listOf(DOLLAR, OTHER_DOLLAR, PERCENTAGE, OTHER_PERCENTAGE)
        val DISCOUNTS_IDS = listOf(DOLLAR_ID, OTHER_DOLLAR_ID, PERCENTAGE_ID, OTHER_PERCENTAGE_ID)

    }


}
