package com.mube.billcalculation.ui.mappers

import com.mube.billcalculation.domain.models.Menu
import com.mube.billcalculation.ui.models.MenuUiState
import com.mube.billcalculation.ui.utils.toCurrency
import com.mube.products.domain.models.Product

internal fun Menu.toUi(): MenuUiState {
    return MenuUiState(
        items = items.map {
            MenuUiState.Item(
                categoryName = it.first.name,
                products = it.second.map { it.toUi() }
            )
        }
    )
}

internal fun Product.toUi(): MenuUiState.Product = MenuUiState.Product(
    id = id,
    name = name,
    formattedPrice = price.toCurrency()
)