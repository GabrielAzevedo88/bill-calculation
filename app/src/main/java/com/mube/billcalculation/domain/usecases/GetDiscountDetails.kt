package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.DiscountsDetail
import com.mube.billcalculation.domain.repository.DraftRepository
import com.mube.discounts.domain.repository.DiscountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetDiscountDetails @Inject constructor(
    private val draftRepository: DraftRepository,
    private val discountRepository: DiscountRepository
) {

    operator fun invoke(): Flow<DiscountsDetail> = draftRepository.getDraft().filterNotNull().map { order ->
        val discounts = discountRepository.getAll()
        val discountItems = discounts.map {
            DiscountsDetail.Item(
                id = it.id,
                name = it.name,
                isSelected = order.discountsIds.contains(it.id)
            )
        }

        DiscountsDetail(discounts = discountItems)
    }

}