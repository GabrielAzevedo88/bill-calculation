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

internal class SetMainsDataTest {

    private val mockCategoriesRepository: CategoriesRepository = mockk {
        coEvery { this@mockk.insert(CATEGORY) } returns CATEGORY_ID
    }
    private val mockProductsRepository: ProductsRepository = mockk(relaxUnitFun = true)

    private val setMainsData = SetMainsData(
        categoriesRepository = mockCategoriesRepository,
        productsRepository = mockProductsRepository
    )

    @Test
    fun `test set mains data`() = runTest {
        setMainsData()

        coVerify {
            mockCategoriesRepository.insert(CATEGORY)
            mockProductsRepository.insertAll(PRODUCTS)
        }
    }

    private companion object {
        const val CATEGORY_ID = 100L
        val CATEGORY = Category(id = 0, name = "Mains")
        val PRODUCTS = listOf(
            Product(
                id = 0,
                name = "Burger",
                price = 9.99f,
                categoryId = CATEGORY_ID
            ),
            Product(
                id = 0,
                name = "Hotdog",
                price = 3.99f,
                categoryId = CATEGORY_ID
            ),
            Product(
                id = 0,
                name = "Pizza",
                price = 12.99f,
                categoryId = CATEGORY_ID
            )
        )
    }

}
