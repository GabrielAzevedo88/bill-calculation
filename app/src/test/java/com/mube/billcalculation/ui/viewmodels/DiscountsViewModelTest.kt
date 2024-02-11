package com.mube.billcalculation.ui.viewmodels

import com.mube.billcalculation.domain.usecases.GetDiscountDetails
import com.mube.billcalculation.domain.usecases.UpdateOrderDraft
import com.mube.billcalculation.utils.CoroutineDispatcherRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineDispatcherRule::class)
class DiscountsViewModelTest {

    private val mockUpdateOrderDraft: UpdateOrderDraft = mockk(relaxed = true)
    private val mockGetDiscountDetails: GetDiscountDetails = mockk {
        every { this@mockk.invoke() } returns flowOf()
    }

    @Test
    fun `initial state should by loading`() = runTest {
        val viewModel = getViewModel()

        val expected = DiscountState.Loading
        val actual = viewModel.state.value

        assertEquals(expected, actual)
    }

    @Test
    fun `test onClick event`() = runTest {
        val viewModel = getViewModel()

        viewModel.eventsHandler(DiscountsEvent.OnClick(DISCOUNT_ID))

        verify { mockUpdateOrderDraft.updateDiscount(DISCOUNT_ID) }
    }

    @Test
    fun `test onClear event`() = runTest {
        val viewModel = getViewModel()

        viewModel.eventsHandler(DiscountsEvent.OnClear)

        verify { mockUpdateOrderDraft.clearDiscounts() }
    }

    private fun getViewModel() : DiscountsViewModel = DiscountsViewModel(
        updateOrderDraft = mockUpdateOrderDraft,
        getDiscountDetails = mockGetDiscountDetails
    )

    private companion object {
        const val DISCOUNT_ID = 100
    }
}