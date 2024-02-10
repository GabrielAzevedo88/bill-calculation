package com.mube.billcalculation.ui.models

import com.mube.products.domain.models.ProductId

data class OrderUiState(
    val items: List<Item>,
    val subtotal: String,
    val discounts: String,
    val tax: String,
    val total: String
) {
    data class Item(
        val productId: ProductId,
        val name: String,
        val quantity: Int,
        val totalPriceFormatted: String
    )
}
