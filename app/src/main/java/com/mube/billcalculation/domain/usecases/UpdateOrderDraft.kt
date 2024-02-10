package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.repository.DraftRepository
import com.mube.discounts.domain.models.DiscountId
import com.mube.products.domain.models.ProductId
import javax.inject.Inject

class UpdateOrderDraft @Inject constructor(
    private val draftRepository: DraftRepository
) {

    fun updateDiscount(discountId: DiscountId) {
        val item = draftRepository.getDraft().value

        item?.let {
            val discountIds = it.discountsIds.toMutableList()

            discountIds.add(discountId)
            val newItem = it.copy(discountsIds = discountIds)

            draftRepository.updateDraft(newItem)
        }
    }

    fun updateItem(productId: ProductId) {
        val draft = draftRepository.getDraft().value ?: return
        val items = draft.items.toMutableList()

        val item = items.find { it.productId == productId } ?: return
        val quantity = item.quantity.inc()

        items.add(Order.Item(productId = productId, categoryId = item.categoryId, name = item.name, price = item.price, quantity = quantity))

        val newDraft = draft.copy(items = items)
        draftRepository.updateDraft(newDraft)
    }
}