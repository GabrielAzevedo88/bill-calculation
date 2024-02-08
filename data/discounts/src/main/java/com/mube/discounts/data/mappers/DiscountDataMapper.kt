package com.mube.discounts.data.mappers

import com.mube.discounts.data.models.DiscountEntity
import com.mube.discounts.data.models.DiscountEntityType
import com.mube.discounts.domain.models.Discount

internal fun DiscountEntity.toDomain(): Discount {
    return when (type) {
        DiscountEntityType.DOLLAR -> Discount.Dollar(
            id = id,
            name = name,
            amount = amount
        )

        DiscountEntityType.PERCENTAGE -> Discount.Percentage(
            id = id,
            name = name,
            amount = amount
        )
    }
}

internal fun Discount.toEntity(): DiscountEntity {
    val type = when (this) {
        is Discount.Dollar -> DiscountEntityType.DOLLAR
        is Discount.Percentage -> DiscountEntityType.PERCENTAGE
    }

    return DiscountEntity(
        id = id,
        name = name,
        amount = amount,
        type = type
    )
}
