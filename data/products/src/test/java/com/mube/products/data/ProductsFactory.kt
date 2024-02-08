package com.mube.products.data

import com.mube.products.data.models.ProductEntity
import com.mube.products.domain.models.Product

internal object ProductsFactory {

    private const val ID = 100
    private const val NAME = "name"
    private const val PRICE = 9.99f
    private const val CATEGORY_ID = 999

    object Entity {
        val PRODUCT = ProductEntity(
            id = ID,
            name = NAME,
            price = PRICE,
            categoryId = CATEGORY_ID
        )
    }

    object Domain {
        val PRODUCT = Product(
            id = ID,
            name = NAME,
            price = PRICE,
            categoryId = CATEGORY_ID
        )
    }

}