package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.AppFactory
import com.mube.billcalculation.domain.models.DiscountsDetail
import com.mube.billcalculation.domain.repository.DraftRepository
import com.mube.discounts.domain.models.Discount
import com.mube.discounts.domain.repository.DiscountRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class GetDiscountDetailsTest {

    private val mockDraftRepository: DraftRepository = mockk()
    private val mockDiscountRepository: DiscountRepository = mockk {
        coEvery { this@mockk.getAll() } returns DISCOUNTS
    }

    private val getDiscountDetails = GetDiscountDetails(
        draftRepository = mockDraftRepository,
        discountRepository = mockDiscountRepository
    )

    @Test
    fun `test get discount details with item selected`() = runTest {
        every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(discountsIds = listOf(DISCOUNT_ID, OTHER_DISCOUNT_ID)))

        val expected = DiscountsDetail(
            discounts = listOf(
                DiscountsDetail.Item(
                    id = DISCOUNT_ID,
                    name = "name",
                    isSelected = true
                ), DiscountsDetail.Item(
                    id = OTHER_DISCOUNT_ID,
                    name = "name",
                    isSelected = true
                )
            )
        )

        val actual = getDiscountDetails().first()

        assertEquals(expected, actual)
    }

    @Test
    fun `test get discount details without item selected`() = runTest {

        every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(discountsIds = emptyList()))

        val expected = DiscountsDetail(
            discounts = listOf(
                DiscountsDetail.Item(
                    id = DISCOUNT_ID,
                    name = "name",
                    isSelected = false
                ), DiscountsDetail.Item(
                    id = OTHER_DISCOUNT_ID,
                    name = "name",
                    isSelected = false
                )
            )
        )

        val actual = getDiscountDetails().first()

        assertEquals(expected, actual)

    }

    private companion object {
        const val DISCOUNT_ID = 100
        const val OTHER_DISCOUNT_ID = 200

        val DISCOUNTS = listOf(
            Discount.Dollar(
                id = DISCOUNT_ID,
                name = "name",
                amount = 5f
            ),
            Discount.Percentage(
                id = OTHER_DISCOUNT_ID,
                name = "name",
                amount = 10f
            )
        )

        val ORDER = AppFactory.Domain.ORDER
    }
}
