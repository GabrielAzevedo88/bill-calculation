package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import javax.inject.Inject

internal class SetAlcoholData @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke() {
        val category = Category(id = 0, name = "Alcohol")
        val categoryId = categoriesRepository.insert(category)

        val products = listOf(
            Product(
                id = 0,
                name = "Beer",
                price = 5f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Cider",
                price = 6f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Wine",
                price = 7f,
                categoryId = categoryId
            )
        )

        productsRepository.insertAll(products)
    }

}