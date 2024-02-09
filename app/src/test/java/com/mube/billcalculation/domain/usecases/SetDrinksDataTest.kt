package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class SetDrinksDataTest {

    private val mockCategoriesRepository: CategoriesRepository = mockk {
        every { this@mockk.insert(CATEGORY) } returns CATEGORY_ID
    }
    private val mockProductsRepository: ProductsRepository = mockk(relaxUnitFun = true)

    private val setDrinksData = SetDrinksData(
        categoriesRepository = mockCategoriesRepository,
        productsRepository = mockProductsRepository
    )

    @Test
    fun `test set drinks data`() {
        setDrinksData()

        verify {
            mockCategoriesRepository.insert(CATEGORY)
            mockProductsRepository.insertAll(PRODUCTS)
        }
    }

    private companion object {
        const val CATEGORY_ID = 100L
        val CATEGORY = Category(id = 0, name = "Drinks")
        val PRODUCTS = listOf(
            Product(
                id = 0,
                name = "Water",
                price = 0f,
                categoryId = CATEGORY_ID
            ),
            Product(
                id = 0,
                name = "Pop",
                price = 2f,
                categoryId = CATEGORY_ID
            ),
            Product(
                id = 0,
                name = "Orange Juice",
                price = 3f,
                categoryId = CATEGORY_ID
            )
        )
    }

}
