package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.repository.DraftRepository
import javax.inject.Inject

class InitializeOrder @Inject constructor(
    private val draftRepository: DraftRepository
) {

    operator fun invoke() {
        val order = Order(
            id = null,
            items = emptyList(),
            discountsIds = emptyList()
        )

        draftRepository.updateDraft(order)
    }

}