package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.discounts.domain.repository.DiscountRepository
import com.mube.products.domain.repository.ProductsRepository
import com.mube.taxes.domain.repository.TaxesRepository
import javax.inject.Inject

internal class HasData @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository,
    private val discountRepository: DiscountRepository,
    private val taxesRepository: TaxesRepository
) {

    suspend operator fun invoke(): Boolean {
        val hasCategories = categoriesRepository.hasData()
        val hasProducts = productsRepository.hasData()
        val hasDiscount = discountRepository.hasData()
        val hasTaxes = taxesRepository.hasData()

        return (hasCategories && hasProducts && hasDiscount && hasTaxes)
    }

}
