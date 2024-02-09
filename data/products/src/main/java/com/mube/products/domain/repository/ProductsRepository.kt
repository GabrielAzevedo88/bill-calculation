package com.mube.products.domain.repository

import com.mube.products.domain.models.Product

interface ProductsRepository {

    suspend fun getAllByCategoryId(categoryId: Int): List<Product>
    suspend fun insertAll(products: List<Product>)

    suspend fun hasData(): Boolean
}