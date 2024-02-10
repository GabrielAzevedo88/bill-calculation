package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.utils.amountOf
import com.mube.billcalculation.utils.percentOf
import com.mube.discounts.domain.models.Discount
import com.mube.discounts.domain.repository.DiscountRepository
import javax.inject.Inject

class GetDiscountsValue @Inject constructor(
    private val discountRepository: DiscountRepository
) {

    suspend operator fun invoke(total: Float, selectedDiscountIds: List<Int>): Float {
        val discounts = discountRepository.getAll()

        if (selectedDiscountIds.isEmpty() || discounts.isEmpty()) return 0f

        val discountsValue = discounts.filter { discount -> discount.id in selectedDiscountIds }.sumOf {
            return@sumOf when (it) {
                is Discount.Percentage -> it.amount percentOf total
                is Discount.Dollar -> it.amount amountOf total
            }.toDouble()
        }

        return discountsValue.toFloat()
    }

}
