package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.AppFactory
import com.mube.billcalculation.domain.repository.DraftRepository
import com.mube.products.domain.models.Product
import com.mube.products.domain.repository.ProductsRepository
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class UpdateOrderDraftTest {

    private val mockDraftRepository: DraftRepository = mockk(relaxed = true) {
        every { this@mockk.getDraft() } returns MutableStateFlow(ORDER)
    }
    private val mockProductsRepository: ProductsRepository = mockk()

    private val updateOrderDraft = UpdateOrderDraft(
        draftRepository = mockDraftRepository,
        productsRepository = mockProductsRepository
    )

    @Nested
    inner class Discounts {

        @Test
        fun `test adding a new one`() {
            every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(discountsIds = emptyList()))

            val order = ORDER.copy(discountsIds = listOf(DISCOUNT_ID))

            updateOrderDraft.updateDiscount(DISCOUNT_ID)

            verify { mockDraftRepository.updateDraft(order) }
        }

        @Test
        fun `test updating a existing one`() {
            every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(discountsIds = listOf(DISCOUNT_ID)))

            val expected = ORDER.copy(discountsIds = emptyList())

            updateOrderDraft.updateDiscount(DISCOUNT_ID)

            verify { mockDraftRepository.updateDraft(expected) }
        }

        @Test
        fun `test clear discounts`() {
            every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(discountsIds = listOf(DISCOUNT_ID)))

            val order = ORDER.copy(discountsIds = emptyList())

            updateOrderDraft.clearDiscounts()

            verify { mockDraftRepository.updateDraft(order) }
        }

    }

    @Nested
    inner class Item {

        @Test
        fun `add a new one`() = runTest {
            every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(items = emptyList()))
            coEvery { mockProductsRepository.getProductById(ITEM_1.productId) } returns Product(
                id = ITEM_1.productId,
                name = ITEM_1.name,
                price = ITEM_1.price,
                categoryId = ITEM_1.categoryId.toLong()
            )

            val expected = ORDER.copy(items = listOf(ITEM_1))

            updateOrderDraft.updateItem(ITEM_1.productId)

            verify { mockDraftRepository.updateDraft(expected) }
        }

        @Test
        fun `update a existing one`() = runTest {
            every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(items = listOf(ITEM_1)))

            val expected = ORDER.copy(items = listOf(ITEM_1.copy(quantity = 2)))

            updateOrderDraft.updateItem(ITEM_1.productId)

            verify { mockDraftRepository.updateDraft(expected) }
        }

        @Test
        fun `test delete`() = runTest {
            every { mockDraftRepository.getDraft() } returns MutableStateFlow(ORDER.copy(items = listOf(ITEM_1)))

            val expected = ORDER.copy(items = emptyList())

            updateOrderDraft.deleteItem(ITEM_1.productId)

            verify { mockDraftRepository.updateDraft(expected) }
        }

    }

    private companion object {
        const val DISCOUNT_ID = 100
        val ITEM_1 = AppFactory.Domain.ITEM_1
        val ORDER = AppFactory.Domain.ORDER.copy(items = listOf(ITEM_1))
    }

}