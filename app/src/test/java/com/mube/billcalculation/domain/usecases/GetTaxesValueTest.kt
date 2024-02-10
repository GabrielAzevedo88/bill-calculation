package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.taxes.domain.models.Taxes
import com.mube.taxes.domain.repository.TaxesRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class GetTaxesValueTest {

    private val mockTaxesRepository: TaxesRepository = mockk {
        coEvery { this@mockk.getAll() } returns TAXES
    }
    private val getTaxesValue = GetTaxesValue(
        taxesRepository = mockTaxesRepository
    )

    @Test
    fun `if items size is zero must return zero`() = runTest {
        val expected = 0f
        val actual = getTaxesValue(items = emptyList())

        assertEquals(expected, actual)
    }

    @Test
    fun `if taxes size is zero must return zero`() = runTest {
        coEvery { mockTaxesRepository.getAll() } returns emptyList()

        val expected = 0f
        val actual = getTaxesValue(items = emptyList())

        assertEquals(expected, actual)
    }

    @Test
    fun `test with regular taxes`() = runTest {
        val expected = 1.04f
        val actual = getTaxesValue(items = listOf(ITEM_WITH_NO_CATEGORY_TAX))

        assertEquals(expected, actual)
    }

    @Test
    fun `test with taxes by category`() = runTest {
        val expected = 2.53f
        val actual = getTaxesValue(items = listOf(ITEM_WITH_CATEGORY_TAX, OTHER_ITEM_WITH_CATEGORY_TAX))

        assertEquals(expected, actual)
    }

    @Test
    fun `test with all taxes`() = runTest{
        val expected = 3.57f
        val actual = getTaxesValue(items = ITEMS)

        assertEquals(expected, actual)
    }

    private companion object {

        const val CATEGORY_ID = 1000
        const val OTHER_CATEGORY_ID = 2000
        const val NO_TAXES_CATEGORY_ID = 3000

        val REGULAR_TAXES = Taxes(
            id = 100,
            name = "taxes",
            amount = 5f,
            categoriesId = null
        )

        val OTHER_REGULAR_TAXES = Taxes(
            id = 200,
            name = "taxes",
            amount = 8f,
            categoriesId = null
        )

        val CATEGORY_TAXES = Taxes(
            id = 200,
            name = "taxes",
            amount = 10f,
            categoriesId = listOf(CATEGORY_ID, OTHER_CATEGORY_ID)
        )

        val TAXES = listOf(REGULAR_TAXES, OTHER_REGULAR_TAXES, CATEGORY_TAXES)

        val ITEM_WITH_NO_CATEGORY_TAX = Order.Item(
            productId = 700,
            categoryId = NO_TAXES_CATEGORY_ID,
            name = "",
            quantity = 1,
            price = 8f
        )

        val ITEM_WITH_CATEGORY_TAX = Order.Item(
            productId = 500,
            categoryId = CATEGORY_ID,
            name = "",
            quantity = 1,
            price = 5f
        )

        val OTHER_ITEM_WITH_CATEGORY_TAX = Order.Item(
            productId = 600,
            categoryId = OTHER_CATEGORY_ID,
            name = "",
            quantity = 1,
            price = 6f
        )

        val ITEMS = listOf(
            ITEM_WITH_CATEGORY_TAX,
            OTHER_ITEM_WITH_CATEGORY_TAX,
            ITEM_WITH_NO_CATEGORY_TAX
        )

    }

}
