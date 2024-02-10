package com.mube.billcalculation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mube.billcalculation.domain.usecases.GetOrderDetails
import com.mube.billcalculation.domain.usecases.InitializeOrder
import com.mube.billcalculation.ui.mappers.toUi
import com.mube.billcalculation.ui.models.OrderUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class OrderViewModel @Inject constructor(
    getOrderDetails: GetOrderDetails,
    initializeOrder: InitializeOrder
) : ViewModel() {

    val state: StateFlow<OrderState> = getOrderDetails().map {
        OrderState.Loaded(content = it.toUi())
    }.stateIn(scope = viewModelScope, initialValue = OrderState.Loading, started = SharingStarted.Eagerly)

    init {
        initializeOrder()
    }

}

internal sealed interface OrderState {
    data object Loading : OrderState
    data class Loaded(val content: OrderUiState) : OrderState
}
