package com.mube.billcalculation.domain.usecases

import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import javax.inject.Inject

internal class SetMainsData @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val productsRepository: ProductsRepository
) {

    suspend operator fun invoke() {
        val category = Category(id = 0, name = "Mains")
        val categoryId = categoriesRepository.insert(category)

        val products = listOf(
            Product(
                id = 0,
                name = "Burger",
                price = 9.99f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Hotdog",
                price = 3.99f,
                categoryId = categoryId
            ),
            Product(
                id = 0,
                name = "Pizza",
                price = 12.99f,
                categoryId = categoryId
            )
        )

        productsRepository.insertAll(products)
    }

}