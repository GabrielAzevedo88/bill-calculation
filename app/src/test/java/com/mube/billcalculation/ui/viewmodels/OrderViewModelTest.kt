package com.mube.billcalculation.ui.viewmodels

import com.mube.billcalculation.domain.usecases.GetOrderDetails
import com.mube.billcalculation.domain.usecases.InitializeOrder
import com.mube.billcalculation.domain.usecases.UpdateOrderDraft
import com.mube.billcalculation.utils.CoroutineDispatcherRule
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineDispatcherRule::class)
class OrderViewModelTest {

    private val mockGetOrderDetails: GetOrderDetails = mockk(relaxed = true)
    private val mockInitializeOrder: InitializeOrder = mockk(relaxed = true)
    private val mockUpdateOrderDraft: UpdateOrderDraft = mockk(relaxed = true)

    @Test
    fun `initial state should by loading`() = runTest {
        val viewModel = getViewModel()

        val expected = OrderState.Loading
        val actual = viewModel.state.value

        assertEquals(expected, actual)
    }

    @Test
    fun `test finalize event`() {
        val viewModel = getViewModel()

        viewModel.eventHandler(OrderEvent.Finalize)

        verify { mockInitializeOrder() }
    }

    @Test
    fun `test delete event`() {
        val viewModel = getViewModel()

        viewModel.eventHandler(OrderEvent.Delete(PRODUCT_ID))

        verify { mockUpdateOrderDraft.deleteItem(PRODUCT_ID) }
    }


    private fun getViewModel() = OrderViewModel(
        getOrderDetails = mockGetOrderDetails,
        initializeOrder = mockInitializeOrder,
        updateOrderDraft = mockUpdateOrderDraft
    )

    private companion object {
        const val PRODUCT_ID = 100
    }
}