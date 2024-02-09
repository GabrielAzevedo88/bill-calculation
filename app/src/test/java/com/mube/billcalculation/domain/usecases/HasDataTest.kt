package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.discounts.domain.repository.DiscountRepository
import com.mube.products.domain.repository.ProductsRepository
import com.mube.taxes.domain.repository.TaxesRepository
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertTrue
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test


internal class HasDataTest {

    private val mockCategoriesRepository: CategoriesRepository = mockk()
    private val mockProductsRepository: ProductsRepository = mockk()
    private val mockDiscountRepository: DiscountRepository = mockk()
    private val mockTaxesRepository: TaxesRepository = mockk()

    private val hasData = HasData(
        categoriesRepository = mockCategoriesRepository,
        productsRepository = mockProductsRepository,
        discountRepository = mockDiscountRepository,
        taxesRepository = mockTaxesRepository
    )

    @Test
    fun `if all repositories has data must return true`() = runTest {
        givenData(hasCategories = true, hasProducts = true, hasDiscounts = true, hasTaxes = true)

        assertTrue(hasData())
    }

    @Test
    fun `if as no categories it must return false`() = runTest {
        givenData(hasCategories = false, hasProducts = true, hasDiscounts = true, hasTaxes = true)

        assertFalse(hasData())
    }

    @Test
    fun `if as no products it must return false`() = runTest {
        givenData(hasCategories = true, hasProducts = false, hasDiscounts = true, hasTaxes = true)

        assertFalse(hasData())
    }

    @Test
    fun `if as no discounts it must return false`() = runTest {
        givenData(hasCategories = true, hasProducts = true, hasDiscounts = false, hasTaxes = true)

        assertFalse(hasData())
    }

    @Test
    fun `if as no taxes it must return false`() = runTest {
        givenData(hasCategories = true, hasProducts = true, hasDiscounts = true, hasTaxes = false)

        assertFalse(hasData())
    }

    private fun givenData(hasCategories: Boolean, hasProducts: Boolean, hasDiscounts: Boolean, hasTaxes: Boolean) {
        coEvery { mockCategoriesRepository.hasData() } returns hasCategories
        coEvery { mockProductsRepository.hasData() } returns hasProducts
        coEvery { mockDiscountRepository.hasData() } returns hasDiscounts
        coEvery { mockTaxesRepository.hasData() } returns hasTaxes
    }

}