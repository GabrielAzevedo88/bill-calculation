package com.mube.billcalculation.ui.mappers

import com.mube.billcalculation.domain.models.DiscountsDetail
import com.mube.billcalculation.ui.models.DiscountUiState
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DiscountUiStateMapperTest {

    @Test
    fun `map from domain to ui`() {
        val expected =     DiscountUiState(
            discountId = DISCOUNT_ID,
            name = DISCOUNT_NAME,
            isSelected = false
        )

        val actual = DiscountsDetail.Item(
            id = DISCOUNT_ID,
            name = DISCOUNT_NAME,
            isSelected = false
        ).toUi()

        assertEquals(expected, actual)
    }

    private companion object {
        const val DISCOUNT_ID = 100
        const val DISCOUNT_NAME = "name"
    }
}