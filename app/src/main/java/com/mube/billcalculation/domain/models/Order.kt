package com.mube.billcalculation.domain.models

import com.mube.categories.domain.models.CategoryId
import com.mube.discounts.domain.models.DiscountId
import com.mube.products.domain.models.ProductId

data class Order(
    val id: Int?,
    val items: List<Item>,
    val discountsIds: List<DiscountId>,
) {

    data class Item(
        val productId: ProductId,
        val categoryId: CategoryId,
        val name: String,
        val quantity: Int,
        val price: Float
    ) {
        fun getTotal(): Float = if (quantity <= 0) 0f else (price * quantity)
    }

}
