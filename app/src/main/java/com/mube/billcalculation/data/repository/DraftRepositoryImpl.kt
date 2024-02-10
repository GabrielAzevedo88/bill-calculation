package com.mube.billcalculation.data.repository

import com.mube.billcalculation.data.datasources.OrderDraftLocalSource
import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.repository.DraftRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class DraftRepositoryImpl @Inject constructor(
    private val localSource: OrderDraftLocalSource
): DraftRepository {

    override fun updateDraft(order: Order) {
        localSource.updateDraft(order)
    }

    override fun getDraft(): StateFlow<Order?> = localSource.getDraft()
}