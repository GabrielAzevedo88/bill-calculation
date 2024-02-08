package com.mube.categories.data.mappers

import com.mube.categories.CategoriesFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CategoriesDataMappersTest {

    @Test
    fun `map from entity to domain`() {
        val expected = DOMAIN
        val actual = ENTITY.toDomain()

        assertEquals(expected, actual)
    }

    @Test
    fun `map from domain to entity`() {
        val expected = ENTITY
        val actual = DOMAIN.toEntity()

        assertEquals(expected, actual)
    }

    private companion object {
        val DOMAIN = CategoriesFactory.Domain.CATEGORY
        val ENTITY = CategoriesFactory.Entity.CATEGORY
    }

}