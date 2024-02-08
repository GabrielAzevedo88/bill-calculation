package com.mube.products.data.mappers

import com.mube.products.data.models.ProductEntity
import com.mube.products.domain.models.Product

internal fun ProductEntity.toDomain(): Product = Product(
    id = id,
    name = name,
    price = price,
    categoryId = categoryId
)

internal fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    name = name,
    price = price,
    categoryId = categoryId
)
