package com.mube.billcalculation.domain.models

import com.mube.discounts.domain.models.DiscountId
import com.mube.products.domain.models.ProductId

data class OrderDetail(
    val items: List<Item>,
    val selectedDiscountsId: List<DiscountId>,
    val amounts: Amounts
) {

    data class Item(
        val productId: ProductId,
        val name: String,
        val quantity: Int,
        val total: Float
    )

    data class Amounts(
        val subtotal: Float,
        val discounts: Float,
        val taxes: Float,
        val total: Float
    )

}