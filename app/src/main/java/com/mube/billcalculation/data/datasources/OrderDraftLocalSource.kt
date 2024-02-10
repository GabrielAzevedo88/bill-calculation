package com.mube.billcalculation.data.datasources

import com.mube.billcalculation.domain.models.Order
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderDraftLocalSource @Inject constructor() {

    private val _draft = MutableStateFlow<Order?>(null)
    private val draft: StateFlow<Order?> = _draft

    fun updateDraft(order: Order) {
        _draft.update { order }
    }

    fun getDraft() = draft

    fun clear() {
        _draft.value = null
    }

}
