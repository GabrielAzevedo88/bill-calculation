package com.mube.taxes.data.mappers

import com.mube.taxes.data.TaxesFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TaxesDataMappersTest {

    @Test
    fun `map from entity to domain`() {
        val expected = TAXES
        val actual = ENTITY_TAXES.toDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map from domain to entity`() {
        val expected = ENTITY_TAXES
        val actual = TAXES.toEntity()

        assertEquals(expected, actual)
    }

    private companion object {
        val TAXES = TaxesFactory.Domain.TAXES
        val ENTITY_TAXES = TaxesFactory.Entity.TAXES
    }
}
