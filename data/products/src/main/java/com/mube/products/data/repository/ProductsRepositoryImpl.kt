package com.mube.products.data.repository

import com.mube.products.data.datasources.ProductsLocalSource
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val localSource: ProductsLocalSource
): ProductsRepository {

    override fun getAllByCategoryId(categoryId: Int): List<Product> = localSource.getAllByCategoryId(categoryId = categoryId)

    override fun insertAll(products: List<Product>) {
        localSource.insertAll(products = products)
    }
}