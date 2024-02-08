package com.mube.discounts.data

import com.mube.discounts.data.models.DiscountEntity
import com.mube.discounts.data.models.DiscountEntityType
import com.mube.discounts.domain.models.Discount

internal object DiscountFactory {

    private val ID = 100
    private val NAME = "name"
    private val AMOUNT = 5f

    object Entity {

        val DISCOUNT = DiscountEntity(
            id = ID,
            name = NAME,
            amount = AMOUNT,
            type = DiscountEntityType.PERCENTAGE
        )

    }

    object Domain {

        val DISCOUNT = Discount.Percentage(
            id = ID,
            name = NAME,
            amount = AMOUNT
        )

    }

}