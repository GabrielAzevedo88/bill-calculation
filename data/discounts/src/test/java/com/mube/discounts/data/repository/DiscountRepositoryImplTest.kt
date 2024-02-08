package com.mube.discounts.data.repository

import com.mube.discounts.data.DiscountFactory
import com.mube.discounts.data.datasources.DiscountLocalSource
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DiscountRepositoryImplTest {

    private val mockLocalSource: DiscountLocalSource = mockk() {
        every { this@mockk.getAll() } returns DISCOUNTS
        justRun { this@mockk.insertAll(any()) }
    }

    private val repository = DiscountRepositoryImpl(localSource = mockLocalSource)

    @Test
    fun `get all must get from local source`() {
        val expected = DISCOUNTS
        val actual = repository.getAll()

        assertEquals(expected, actual)

        verify { mockLocalSource.getAll() }
    }

    @Test
    fun `insert all must insert into local source`() {
        repository.insertAll(discounts = DISCOUNTS)

        verify { mockLocalSource.insertAll(discounts = DISCOUNTS) }
    }

    private companion object {

        val DISCOUNTS = listOf(
            DiscountFactory.Domain.DISCOUNT
        )

    }

}