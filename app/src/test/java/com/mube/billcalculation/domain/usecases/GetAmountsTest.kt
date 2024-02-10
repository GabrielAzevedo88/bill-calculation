package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.models.OrderDetail
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class GetAmountsTest {

    private val mockGetDiscountsValue: GetDiscountsValue = mockk()
    private val mockGetTaxesValue: GetTaxesValue = mockk()

    private val getAmounts = GetAmounts(
        getDiscountsValue = mockGetDiscountsValue,
        getTaxesValue = mockGetTaxesValue
    )

    @Test
    fun `test without taxes`() = runTest {
        givenValues(taxes = 0f)

        val expected = OrderDetail.Amounts(
            subtotal = 19f,
            discounts = 5f,
            taxes = 0f,
            total = 14f
        )

        val actual = getAmounts(order = ORDER)

        assertEquals(expected, actual)
    }

    @Test
    fun `test without discounts`() = runTest {
        givenValues(discount = 0f)

        val expected = OrderDetail.Amounts(
            subtotal = 19f,
            discounts = 0f,
            taxes = 3f,
            total = 22f
        )
        val actual = getAmounts(order = ORDER)

        assertEquals(expected, actual)
    }

    @Test
    fun `test without taxes and discounts`() = runTest {
        givenValues(discount = 0f, taxes = 0f)

        val expected = OrderDetail.Amounts(
            subtotal = 19f,
            discounts = 0f,
            taxes = 0f,
            total = 19f
        )
        val actual = getAmounts(order = ORDER)

        assertEquals(expected, actual)
    }

    @Test
    fun `test with taxes and discounts`() = runTest {
        givenValues()

        val expected = OrderDetail.Amounts(
            subtotal = 19f,
            discounts = 5f,
            taxes = 3f,
            total = 17f
        )
        val actual = getAmounts(order = ORDER)

        assertEquals(expected, actual)
    }

    private fun givenValues(discount: Float = 5f, taxes: Float = 3f) {
        coEvery { mockGetDiscountsValue.invoke(total = any(), selectedDiscountIds = emptyList()) } returns discount
        coEvery { mockGetTaxesValue.invoke(items = ITEMS) } returns taxes
    }

    private companion object {

        val ITEM_1 = Order.Item(
            productId = 700,
            categoryId = 100,
            name = "",
            quantity = 1,
            price = 8f
        )

        val ITEM_2 = Order.Item(
            productId = 500,
            categoryId = 100,
            name = "",
            quantity = 1,
            price = 5f
        )

        val ITEM_3 = Order.Item(
            productId = 600,
            categoryId = 100,
            name = "",
            quantity = 1,
            price = 6f
        )

        val ITEMS = listOf(ITEM_1, ITEM_2, ITEM_3)

        val ORDER = Order(
            id = null,
            items = ITEMS,
            discountsIds = emptyList()
        )

    }

}