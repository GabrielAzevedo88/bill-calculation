package com.mube.products.domain.repository

import com.mube.products.domain.models.Product
import com.mube.products.domain.models.ProductId

interface ProductsRepository {

    suspend fun getAllByCategoryId(categoryId: Int): List<Product>
    suspend fun insertAll(products: List<Product>)

    suspend fun hasData(): Boolean

    suspend fun getProductById(productId: Int): Product?
}