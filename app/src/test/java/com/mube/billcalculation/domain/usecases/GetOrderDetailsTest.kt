package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.AppFactory
import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.models.OrderDetail
import com.mube.billcalculation.domain.repository.DraftRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


private class GetOrderDetailsTest {

    private val mockDraftRepository: DraftRepository = mockk {
        coEvery { this@mockk.getDraft() } returns MutableStateFlow(ORDER)
    }
    private val mockGetAmounts: GetAmounts = mockk {
        coEvery { this@mockk.invoke(order = ORDER) } returns AMOUNTS
    }
    private val getOrderDetails = GetOrderDetails(
        draftRepository = mockDraftRepository,
        getAmounts = mockGetAmounts
    )

    @Test
    fun `test get details`() = runTest {
        val expected = OrderDetail(
            items = ITEMS,
            amounts = AMOUNTS,
            selectedDiscountsId = ORDER.discountsIds
        )
        val actual = getOrderDetails().first()

        assertEquals(expected, actual)
    }

    private companion object {
        val ORDER = AppFactory.Domain.ORDER
        val AMOUNTS = OrderDetail.Amounts(
            subtotal = 0f,
            discounts = 0f,
            taxes = 0f,
            total = 0f
        )
        val ITEMS = listOf(
            OrderDetail.Item(
                productId = 700,
                name = "",
                quantity = 1,
                total = 8f
            ),
            OrderDetail.Item(
                productId = 500,
                name = "",
                quantity = 1,
                total = 5f
            ),
            OrderDetail.Item(
                productId = 600,
                name = "",
                quantity = 1,
                total = 6f
            )
        )
    }

}