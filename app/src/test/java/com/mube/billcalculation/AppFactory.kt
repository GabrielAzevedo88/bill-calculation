package com.mube.billcalculation

import com.mube.billcalculation.domain.models.Order

internal object AppFactory {

    object Domain {

        val ITEM_1 = Order.Item(
            productId = 700,
            categoryId = 100,
            name = "",
            quantity = 1,
            price = 8f
        )

        val ITEM_2 = Order.Item(
            productId = 500,
            categoryId = 100,
            name = "",
            quantity = 1,
            price = 5f
        )

        val ITEM_3 = Order.Item(
            productId = 600,
            categoryId = 100,
            name = "",
            quantity = 1,
            price = 6f
        )

        val ITEMS = listOf(ITEM_1, ITEM_2, ITEM_3)

        val ORDER = Order(
            id = null,
            items = ITEMS,
            discountsIds = emptyList()
        )

    }

}