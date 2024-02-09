package com.mube.billcalculation.ui.mappers

import com.mube.billcalculation.domain.models.Menu
import com.mube.billcalculation.ui.models.MenuUiState
import com.mube.categories.domain.models.Category
import com.mube.products.domain.models.Product
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class MenuUiStateMapperTest {

    @Test
    fun `map from domain to ui`() {
        val expected = MenuUiState(
            items = listOf(
                MenuUiState.Item(
                    categoryName = CATEGORY_NAME,
                    products = listOf(
                        MenuUiState.Product(
                            id = 200,
                            name = PRODUCT_NAME,
                            formattedPrice = "$12.99"
                        )
                    )
                )
            )
        )
        val actual = MENU.toUi()

        assertEquals(expected, actual)
    }

    private companion object {
        const val CATEGORY_NAME = "category"
        const val PRODUCT_NAME = "product"
        const val CATEGORY_ID = 100
        const val PRODUCT_ID = 200

        val CATEGORY = Category(
            id = CATEGORY_ID,
            name = CATEGORY_NAME
        )
        val PRODUCT = Product(
            id = PRODUCT_ID,
            name = PRODUCT_NAME,
            price = 12.99f,
            categoryId = CATEGORY_ID.toLong()
        )
        val MENU = Menu(
            items = listOf(
                CATEGORY to listOf(PRODUCT)
            )
        )
    }
}