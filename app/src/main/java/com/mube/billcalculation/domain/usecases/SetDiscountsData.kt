package com.mube.billcalculation.domain.usecases

import com.mube.discounts.domain.models.Discount
import com.mube.discounts.domain.repository.DiscountRepository
import javax.inject.Inject

internal class SetDiscountsData @Inject constructor(
    private val discountRepository: DiscountRepository
) {

    operator fun invoke() {
        val discounts = listOf(
            Discount.Dollar(
                id = 0,
                name = "$5 off",
                amount = 5f
            ),
            Discount.Percentage(
                id = 0,
                name = "10% off",
                amount = 10f
            ),
            Discount.Percentage(
                id = 0,
                name = "20% off",
                amount = 20f
            )
        )

        discountRepository.insertAll(discounts)

    }

}