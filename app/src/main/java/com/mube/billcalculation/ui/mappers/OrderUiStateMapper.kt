package com.mube.billcalculation.ui.mappers

import com.mube.billcalculation.domain.models.OrderDetail
import com.mube.billcalculation.ui.models.OrderUiState
import com.mube.billcalculation.utils.toCurrency

internal fun OrderDetail.toUi(): OrderUiState {
    return OrderUiState(
        items = items.map { it.toUi() },
        subtotal = amounts.subtotal.toCurrency(),
        discounts = amounts.discounts.toCurrency(),
        tax = amounts.taxes.toCurrency(),
        total = amounts.total.toCurrency()
    )
}

private fun OrderDetail.Item.toUi(): OrderUiState.Item {
    return OrderUiState.Item(
        productId = productId,
        name = name,
        quantity = quantity,
        totalPriceFormatted = total.toCurrency()
    )
}