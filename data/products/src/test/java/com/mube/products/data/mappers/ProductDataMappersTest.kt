package com.mube.products.data.mappers

import com.mube.products.data.ProductsFactory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ProductDataMappersTest {

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
        val ENTITY = ProductsFactory.Entity.PRODUCT
        val DOMAIN = ProductsFactory.Domain.PRODUCT
    }

}