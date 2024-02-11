package com.mube.billcalculation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mube.billcalculation.domain.usecases.GetMenu
import com.mube.billcalculation.domain.usecases.InitialDataLoading
import com.mube.billcalculation.domain.usecases.UpdateOrderDraft
import com.mube.billcalculation.ui.mappers.toUi
import com.mube.billcalculation.ui.models.MenuUiState
import com.mube.products.domain.models.ProductId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class MenuViewModel @Inject constructor(
    initialDataLoading: InitialDataLoading,
    getMenu: GetMenu,
    private val updateOrderDraft: UpdateOrderDraft
) : ViewModel() {

    val state: StateFlow<MenuState> = getMenu().map {
        MenuState.Loaded(content = it.toUi())
    }.stateIn(scope = viewModelScope, initialValue = MenuState.Loading, started = SharingStarted.Eagerly)

    init {
        viewModelScope.launch {
            initialDataLoading()
        }
    }

    fun eventHandler(menuEvent: MenuEvent) {
        when(menuEvent) {
            is MenuEvent.ProductClick -> viewModelScope.launch {
                updateOrderDraft.updateItem(menuEvent.productId)
            }
        }
    }

}

internal sealed interface MenuState {
    data object Loading : MenuState
    data class Loaded(val content: MenuUiState) : MenuState
}


internal sealed interface MenuEvent {
    data class ProductClick(val productId: ProductId): MenuEvent
}