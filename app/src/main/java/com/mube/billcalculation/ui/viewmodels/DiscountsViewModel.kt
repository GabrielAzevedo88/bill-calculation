package com.mube.billcalculation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mube.billcalculation.domain.repository.DraftRepository
import com.mube.billcalculation.domain.usecases.GetDiscountDetails
import com.mube.billcalculation.domain.usecases.UpdateOrderDraft
import com.mube.billcalculation.ui.mappers.toUi
import com.mube.billcalculation.ui.models.DiscountUiState
import com.mube.discounts.domain.models.DiscountId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class DiscountsViewModel @Inject constructor(
    private val updateOrderDraft: UpdateOrderDraft,
    getDiscountDetails: GetDiscountDetails
) : ViewModel() {

    val state: StateFlow<DiscountState> = getDiscountDetails().map {
        DiscountState.Loaded(content = it.discounts.map {
            DiscountUiState(
                discountId = it.id,
                name = it.name,
                isSelected = it.isSelected
            )
        })
    }.stateIn(scope = viewModelScope, initialValue = DiscountState.Loading, started = SharingStarted.Eagerly)

    fun eventsHandler(event: DiscountsEvent) {
        when (event) {
            is DiscountsEvent.OnClick -> updateOrderDraft.updateDiscount(event.discountId)
            is DiscountsEvent.OnClear -> updateOrderDraft.clearDiscounts()
        }
    }

}

sealed interface DiscountState {
    data object Loading : DiscountState
    data class Loaded(val content: List<DiscountUiState>) : DiscountState
}

sealed interface DiscountsEvent {
    data class OnClick(val discountId: DiscountId) : DiscountsEvent
    data object OnClear : DiscountsEvent
}