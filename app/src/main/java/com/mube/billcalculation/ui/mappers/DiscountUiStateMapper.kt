package com.mube.billcalculation.ui.mappers

import com.mube.billcalculation.domain.models.DiscountsDetail
import com.mube.billcalculation.ui.models.DiscountUiState

 internal fun DiscountsDetail.Item.toUi() : DiscountUiState =  DiscountUiState(
    discountId = id,
    name = name,
    isSelected = isSelected
)