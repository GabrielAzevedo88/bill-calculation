package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import javax.inject.Inject

internal class SetAppetizersData @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository
) {

    operator fun invoke() {
        val category = Category(id = 0, name = "Appetizers")
        val categoryId = categoriesRepository.insert(category)

        val products = listOf(
            Product(
                id = 0,
                name = "Nachos",
                price = 13.99f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Calamari",
                price = 11.99f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Caesar Salad",
                price = 10.99f,
                categoryId = categoryId
            )
        )

        productsRepository.insertAll(products)
    }

}