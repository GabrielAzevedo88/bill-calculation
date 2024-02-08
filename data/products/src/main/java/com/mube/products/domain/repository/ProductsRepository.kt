package com.mube.products.domain.repository

import com.mube.products.domain.models.Product

interface ProductsRepository {

    fun getAllByCategoryId(categoryId: Int): List<Product>
    fun insertAll(products: List<Product>)

}