package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Menu
import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GetMenuTest {

    private val mockCategoriesRepository: CategoriesRepository = mockk {
        coEvery { this@mockk.getAll() } returns flowOf(listOf(CATEGORY))
    }
    private val mockProductRepository: ProductsRepository = mockk {
        coEvery { this@mockk.getAllByCategoryId(CATEGORY_ID) } returns listOf(PRODUCT)
    }

    private val getMenu = GetMenu(
        categoriesRepository = mockCategoriesRepository,
        productRepository = mockProductRepository
    )

    @Test
    fun `must return a pair with category and list of products`() = runTest {
        val expected = Menu(items = listOf(Pair(CATEGORY, listOf(PRODUCT))))
        val actual = getMenu().first()

        assertEquals(expected, actual)
    }

    private companion object {
        const val CATEGORY_ID = 100
        val CATEGORY = Category(
            id = CATEGORY_ID,
            name = "name"
        )

        val PRODUCT = Product(
            id = 200,
            name = "name",
            price = 2f,
            categoryId = CATEGORY_ID.toLong()
        )
    }
}