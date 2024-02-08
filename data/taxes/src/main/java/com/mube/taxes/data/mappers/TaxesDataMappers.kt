package com.mube.taxes.data.mappers

import com.mube.taxes.data.models.TaxesEntity
import com.mube.taxes.domain.models.Taxes

internal fun TaxesEntity.toDomain(): Taxes {
    return Taxes(
        id = id,
        name = name,
        amount = amount,
        categoriesId = categoriesId
    )
}

internal fun Taxes.toEntity(): TaxesEntity {
    return TaxesEntity(
        id = id,
        name = name,
        amount = amount,
        categoriesId = categoriesId
    )
}