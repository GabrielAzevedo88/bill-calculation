package com.mube.billcalculation.domain.repository

import com.mube.billcalculation.domain.models.Order
import kotlinx.coroutines.flow.StateFlow

interface DraftRepository {

    fun updateDraft(order: Order)

    fun getDraft(): StateFlow<Order?>

}