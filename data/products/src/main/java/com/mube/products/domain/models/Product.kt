package com.mube.products.domain.models

data class Product(
    val id: ProductId,
    val name: String,
    val price: Float,
    val categoryId: Long
)
