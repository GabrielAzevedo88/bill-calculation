package com.mube.products.data.repository

import com.mube.products.data.ProductsFactory
import com.mube.products.data.datasources.ProductsLocalSource
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ProductsRepositoryImplTest {

    private val mockLocalSource: ProductsLocalSource = mockk() {
        every { this@mockk.getAllByCategoryId(categoryId = CATEGORY_ID) } returns PRODUCTS
        justRun { this@mockk.insertAll(any()) }
    }

    private val repository = ProductsRepositoryImpl(localSource = mockLocalSource)

    @Test
    fun `get all must get from local source`() {
        val expected = PRODUCTS
        val actual = repository.getAllByCategoryId(categoryId = CATEGORY_ID)

        assertEquals(expected, actual)

        verify { mockLocalSource.getAllByCategoryId(categoryId = CATEGORY_ID) }
    }

    @Test
    fun `insert all must insert into local source`() {
        repository.insertAll(products = PRODUCTS)

        verify { mockLocalSource.insertAll(products = PRODUCTS) }
    }

    private companion object {
        const val CATEGORY_ID = 100
        val PRODUCTS = listOf(ProductsFactory.Domain.PRODUCT)
    }


}