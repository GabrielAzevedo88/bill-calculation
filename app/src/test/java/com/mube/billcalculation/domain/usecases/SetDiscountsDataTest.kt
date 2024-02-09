package com.mube.billcalculation.domain.usecases

import com.mube.discounts.domain.models.Discount
import com.mube.discounts.domain.repository.DiscountRepository
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test

internal class SetDiscountsDataTest {

    private val mockDiscountRepository: DiscountRepository = mockk(relaxUnitFun = true)

    private val setDiscountsData = SetDiscountsData(discountRepository = mockDiscountRepository)

    @Test
    fun `test set discount data`() = runTest {
        setDiscountsData()

        coVerify { mockDiscountRepository.insertAll(DISCOUNTS) }
    }

    private companion object {
        val DISCOUNTS = listOf(
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
    }

}