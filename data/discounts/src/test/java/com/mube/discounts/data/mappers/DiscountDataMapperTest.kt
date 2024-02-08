package com.mube.discounts.data.mappers

import com.mube.discounts.data.DiscountFactory
import com.mube.discounts.data.models.DiscountEntityType
import com.mube.discounts.domain.models.Discount
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class DiscountDataMapperTest {

    @ParameterizedTest
    @EnumSource(DiscountEntityType::class)
    fun `map from entity to domain`(type: DiscountEntityType) {
        val expected = when (type) {
            DiscountEntityType.DOLLAR -> DOLLAR_DISCOUNT
            DiscountEntityType.PERCENTAGE -> PERCENTAGE_DISCOUNT
        }

        val actual = ENTITY_DISCOUNT.copy(type = type).toDomain()

        assertEquals(expected, actual)
    }

    @ParameterizedTest
    @EnumSource(DiscountEntityType::class)
    fun `map from domain to entity`(type: DiscountEntityType) {
        val input = when (type) {
            DiscountEntityType.DOLLAR -> DOLLAR_DISCOUNT
            DiscountEntityType.PERCENTAGE -> PERCENTAGE_DISCOUNT
        }

        val expected = ENTITY_DISCOUNT.copy(type = type)
        val actual = input.toEntity()

        assertEquals(expected, actual)
    }

    private companion object {
        val DISCOUNT = DiscountFactory.Domain.DISCOUNT
        val ENTITY_DISCOUNT = DiscountFactory.Entity.DISCOUNT
        val DOLLAR_DISCOUNT = Discount.Dollar(id = DISCOUNT.id, name = DISCOUNT.name, amount = DISCOUNT.amount)
        val PERCENTAGE_DISCOUNT = Discount.Percentage(id = DISCOUNT.id, name = DISCOUNT.name, amount = DISCOUNT.amount)
    }


}