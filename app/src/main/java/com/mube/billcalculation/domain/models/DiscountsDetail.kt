package com.mube.billcalculation.domain.models

import com.mube.discounts.domain.models.DiscountId

data class DiscountsDetail(
    val discounts: List<Item>
) {
    data class Item(
        val id: DiscountId,
        val name: String,
        val isSelected: Boolean
    )
}
