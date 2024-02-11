package com.mube.billcalculation.ui.models

import com.mube.discounts.domain.models.DiscountId

data class DiscountUiState(
    val discountId: DiscountId,
    val name: String,
    val isSelected: Boolean
)
