package com.mube.products.data.datasources

import com.mube.products.data.ProductsFactory
import com.mube.products.data.database.ProductsDao
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ProductsLocalSourceTest {

    private val mockProductsDao: ProductsDao = mockk {
        coEvery { this@mockk.getAllByCategoryId(categoryId = CATEGORY_ID) } returns PRODUCTS_ENTITIES
        coJustRun { this@mockk.insertAll(any()) }
    }

    private val localSource = ProductsLocalSource(dao = mockProductsDao)

    @Test
    fun `test get all entity models should return domain models`() = runTest {
        val expected = PRODUCTS
        val actual = localSource.getAllByCategoryId(CATEGORY_ID)

        assertEquals(expected, actual)
    }

    @Test
    fun `test inserting domain models, must save them as an entity`() = runTest {
        localSource.insertAll(PRODUCTS)

        coVerify { mockProductsDao.insertAll(*PRODUCTS_ENTITIES.toTypedArray()) }
    }

    private companion object {
        private const val CATEGORY_ID = 999
        private val PRODUCTS = listOf(ProductsFactory.Domain.PRODUCT)
        private val PRODUCTS_ENTITIES = listOf(ProductsFactory.Entity.PRODUCT)
    }

}
