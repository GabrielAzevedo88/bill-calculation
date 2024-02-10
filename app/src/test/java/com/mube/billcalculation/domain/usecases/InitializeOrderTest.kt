package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.domain.repository.DraftRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

private class InitializeOrderTest {

    val mockDraftRepository: DraftRepository = mockk(relaxed = true)
    val initializeOrder = InitializeOrder(
        draftRepository = mockDraftRepository
    )

    @Test
    fun `must initialize with a empty object`() {
        initializeOrder()

        verify {
            mockDraftRepository.updateDraft(
                Order(
                    id = null,
                    items = emptyList(),
                    discountsIds = emptyList()
                )
            )
        }
    }

}