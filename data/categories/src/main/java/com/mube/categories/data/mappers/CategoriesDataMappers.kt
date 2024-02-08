package com.mube.categories.data.mappers

import com.mube.categories.data.models.CategoryEntity
import com.mube.categories.domain.models.Category

internal fun CategoryEntity.toDomain(): Category = Category(
    id = id,
    name = name
)

internal fun Category.toEntity(): CategoryEntity = CategoryEntity(
    id = id,
    name = name
)