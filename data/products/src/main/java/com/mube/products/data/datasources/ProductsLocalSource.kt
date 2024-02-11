package com.mube.products.data.datasources

import com.mube.products.data.database.ProductsDao
import com.mube.products.data.mappers.toDomain
import com.mube.products.data.mappers.toEntity
import com.mube.products.domain.models.Product
import javax.inject.Inject

class ProductsLocalSource @Inject constructor(
    private val dao: ProductsDao
) {

    suspend fun getAllByCategoryId(categoryId: Int) = dao.getAllByCategoryId(categoryId = categoryId).map { it.toDomain() }

     suspend fun insertAll(products: List<Product>) {
        dao.insertAll(*products.map { it.toEntity() }.toTypedArray())
    }

    suspend fun hasData(): Boolean = dao.count() > 0

    suspend fun getProductById(productId: Int): Product? = dao.getProductById(productId = productId)?.toDomain()

}