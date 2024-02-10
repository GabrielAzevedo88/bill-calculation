package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.OrderDetail
import com.mube.billcalculation.domain.repository.DraftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetOrderDetails @Inject constructor(
    private val draftRepository: DraftRepository,
    private val getAmounts: GetAmounts
) {

    operator fun invoke(): Flow<OrderDetail> = draftRepository.getDraft().filterNotNull().map {order ->
        val detailItems = order.items.map {
            OrderDetail.Item(
                productId = it.productId,
                name = it.name,
                quantity = it.quantity,
                total = it.getTotal()
            )
        }

        OrderDetail(
            items = detailItems,
            amounts = getAmounts(order),
            selectedDiscountsId = order.discountsIds
        )
    }
}
