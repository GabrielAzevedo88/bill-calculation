package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class SetAppetizersDataTest {

    private val mockCategoriesRepository: CategoriesRepository = mockk {
        coEvery { this@mockk.insert(CATEGORY) } returns CATEGORY_ID
    }
    private val mockProductsRepository: ProductsRepository = mockk(relaxUnitFun = true)

    private val setAppetizersData = SetAppetizersData(
        categoriesRepository = mockCategoriesRepository,
        productsRepository = mockProductsRepository
    )

    @Test
    fun `set appetizers data`() = runTest {
        setAppetizersData()

        coVerify {
            mockCategoriesRepository.insert(CATEGORY)
            mockProductsRepository.insertAll(PRODUCTS)
        }
    }

    private companion object {
        const val CATEGORY_ID = 100L
        val CATEGORY = Category(id = 0, name = "Appetizers")
        val PRODUCTS = listOf(
            Product(
                id = 0,
                name = "Nachos",
                price = 13.99f,
                categoryId = CATEGORY_ID
            ),
            Product(
                id = 0,
                name = "Calamari",
                price = 11.99f,
                categoryId = CATEGORY_ID
            ),
            Product(
                id = 0,
                name = "Caesar Salad",
                price = 10.99f,
                categoryId = CATEGORY_ID
            )
        )
    }

}
