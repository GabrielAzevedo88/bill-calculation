package com.mube.products.data.repository

import com.mube.products.data.datasources.ProductsLocalSource
import com.mube.products.domain.models.Product
import com.mube.products.domain.models.ProductId
import com.mube.products.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val localSource: ProductsLocalSource
): ProductsRepository {

    override suspend fun getAllByCategoryId(categoryId: Int): List<Product> = localSource.getAllByCategoryId(categoryId = categoryId)

    override suspend fun insertAll(products: List<Product>) {
        localSource.insertAll(products = products)
    }

    override suspend fun hasData(): Boolean = localSource.hasData()

    override suspend fun getProductById(productId: ProductId): Product? = localSource.getProductById(productId)
}