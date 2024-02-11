package com.mube.billcalculation.ui.viewmodels

import com.mube.billcalculation.domain.usecases.GetMenu
import com.mube.billcalculation.domain.usecases.InitialDataLoading
import com.mube.billcalculation.domain.usecases.UpdateOrderDraft
import com.mube.billcalculation.utils.CoroutineDispatcherRule
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(CoroutineDispatcherRule::class)
class MenuViewModelTest {

    private val mockInitialDataLoading: InitialDataLoading = mockk(relaxed = true)
    private val mockGetMenu: GetMenu = mockk(relaxed = true)
    private val mockUpdateOrderDraft: UpdateOrderDraft = mockk(relaxed = true)

    @Test
    fun `initial state should by loading`() = runTest {
        val viewModel = getViewModel()

        val expected = MenuState.Loading
        val actual = viewModel.state.value

        assertEquals(expected, actual)
    }

    @Test
    fun `test product click event`() {
        val viewModel = getViewModel()

        viewModel.eventHandler(MenuEvent.ProductClick(PRODUCT_ID))

        coVerify { mockUpdateOrderDraft.updateItem(PRODUCT_ID) }
    }

    private fun getViewModel(): MenuViewModel = MenuViewModel(
        initialDataLoading = mockInitialDataLoading,
        getMenu = mockGetMenu,
        updateOrderDraft = mockUpdateOrderDraft
    )

    private companion object {
        const val PRODUCT_ID = 100
    }
}