package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.taxes.domain.models.Taxes
import com.mube.taxes.domain.repository.TaxesRepository
import javax.inject.Inject

internal class SetTaxesData @Inject constructor(
    private val taxesRepository: TaxesRepository,
    private val categoriesRepository: CategoriesRepository
) {

    operator fun invoke() {
        val alcoholCategoryId = categoriesRepository.getByName("Alcohol").id

        val taxes = listOf(
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
                categoriesId = listOf(alcoholCategoryId)
            )
        )

        taxesRepository.insertAll(taxes)

    }

}