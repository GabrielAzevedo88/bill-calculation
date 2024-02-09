package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import javax.inject.Inject

internal class SetDrinksData @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke() {
        val category = Category(id = 0, name = "Drinks")
        val categoryId = categoriesRepository.insert(category)

        val products = listOf(
            Product(
                id = 0,
                name = "Water",
                price = 0f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Pop",
                price = 2f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Orange Juice",
                price = 3f,
                categoryId = categoryId
            )
        )

        productsRepository.insertAll(products)
    }

}