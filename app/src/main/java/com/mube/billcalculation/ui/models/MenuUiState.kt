package com.mube.billcalculation.ui.models

import com.mube.products.domain.models.ProductId

data class MenuUiState(
    val items: List<Item>
){
    data class Product(
        val id: ProductId,
        val name: String,
        val formattedPrice: String
    )

    data class Item(
        val categoryName: String,
        val products: List<Product>
    )
}
