package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.taxes.domain.models.Taxes
import com.mube.taxes.domain.repository.TaxesRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test


internal class SetTaxesDataTest {

    private val mockTaxesRepository: TaxesRepository = mockk(relaxUnitFun = true)
    private val mockCategoriesRepository: CategoriesRepository = mockk {
        every { this@mockk.getByName(CATEGORY_NAME) } returns CATEGORY
    }

    private val setTaxesData = SetTaxesData(
        taxesRepository = mockTaxesRepository,
        categoriesRepository = mockCategoriesRepository
    )

    @Test
    fun `test set taxes data`() {
        setTaxesData()

        verify {
            mockCategoriesRepository.getByName(CATEGORY_NAME)
            mockTaxesRepository.insertAll(TAXES)
        }
    }

    private companion object {
        const val CATEGORY_NAME = "Alcohol"
        val CATEGORY = Category(
            id = 100,
            name = "Alcohol"
        )

        val TAXES = listOf(
            Taxes(
                id = 0,
                name = "Tax 1",
                amount = 5f,
                categoriesId = null
            ),
            Taxes(
                id = 0,
                name = "Tax 2",
                amount = 8f,
                categoriesId = null
            ),
            Taxes(
                id = 0,
                name = "Alcohol Tax",
                amount = 5f,
                categoriesId = listOf(CATEGORY.id)
            )
        )

    }

}
