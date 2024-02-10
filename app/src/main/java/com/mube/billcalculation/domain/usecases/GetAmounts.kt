package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.models.OrderDetail
import javax.inject.Inject

class GetAmounts @Inject constructor(
    private val getDiscountsValue: GetDiscountsValue,
    private val getTaxesValue: GetTaxesValue
) {

    suspend operator fun invoke(order: Order): OrderDetail.Amounts {
        val taxes = getTaxesValue(items = order.items)
        val subtotal = order.items.sumOf { it.getTotal().toDouble() }.toFloat()
        val subtotalWithTaxes = (subtotal + taxes)
        val discountAmount = getDiscountsValue(total = subtotalWithTaxes, selectedDiscountIds = order.discountsIds)
        val grandTotal = (subtotalWithTaxes - discountAmount)

        return OrderDetail.Amounts(
            subtotal = subtotal,
            discounts = discountAmount,
            taxes = taxes,
            total = grandTotal
        )
    }

}