package com.mube.taxes.domain.models

data class Taxes(
    val id: TaxesId,
    val name: String,
    val amount: Float,
    val categoriesId: List<Int>?
)
