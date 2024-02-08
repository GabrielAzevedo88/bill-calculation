package com.mube.discounts.domain.models

sealed class Discount(
    open val id: Int,
    open val name: String,
    open val amount: Float
) {

    data class Dollar(
        override val id: Int,
        override val name: String,
        override val amount: Float
    ) : Discount(id, name, amount)

    data class Percentage(
        override val id: Int,
        override val name: String,
        override val amount: Float
    ) : Discount(id, name, amount)

}