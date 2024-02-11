package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.repository.DraftRepository
import com.mube.discounts.domain.models.DiscountId
import com.mube.products.domain.models.ProductId
import com.mube.products.domain.repository.ProductsRepository
import javax.inject.Inject

class UpdateOrderDraft @Inject constructor(
    private val draftRepository: DraftRepository,
    private val productsRepository: ProductsRepository
) {

    fun updateDiscount(discountId: DiscountId) {
        draftRepository.getDraft().value?.let {
            val discountIds = it.discountsIds.toMutableList()

            if (discountIds.contains(discountId)) {
                discountIds.remove(discountId)
            } else {
                discountIds.add(discountId)
            }

            val newItem = it.copy(discountsIds = discountIds)
            draftRepository.updateDraft(newItem)
        }
    }

    fun clearDiscounts() {
        draftRepository.getDraft().value?.let {
            draftRepository.updateDraft(it.copy(discountsIds = emptyList()))
        }
    }

    suspend fun updateItem(productId: ProductId) {
        val draft = draftRepository.getDraft().value ?: return
        val items = draft.items.toMutableList()

        val index = items.indexOfFirst { it.productId == productId }

        if (index != -1) {
            val item = items[index]
            items.removeAt(index)
            items.add(
                index = index,
                getOrderItem(
                    productId = productId,
                    categoryId = item.categoryId,
                    name = item.name,
                    price = item.price,
                    quantity = item.quantity.inc()
                )
            )
        } else {
            val product = productsRepository.getProductById(productId) ?: return
            items.add(
                getOrderItem(
                    productId = productId,
                    categoryId = product.categoryId.toInt(),
                    name = product.name,
                    price = product.price,
                    quantity = 1
                )
            )
        }

        val newDraft = draft.copy(items = items)
        draftRepository.updateDraft(newDraft)
    }

    private fun getOrderItem(productId: Int, categoryId: Int, name: String, price: Float, quantity: Int) =
        Order.Item(productId = productId, categoryId = categoryId, name = name, price = price, quantity = quantity)
}

