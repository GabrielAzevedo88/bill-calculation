package com.mube.discounts.data.repository

import com.mube.discounts.data.DiscountFactory
import com.mube.discounts.data.datasources.DiscountLocalSource
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class DiscountRepositoryImplTest {

    private val mockLocalSource: DiscountLocalSource = mockk() {
        coEvery { this@mockk.getAll() } returns DISCOUNTS
        coJustRun { this@mockk.insertAll(any()) }
    }

    private val repository = DiscountRepositoryImpl(localSource = mockLocalSource)

    @Test
    fun `get all must get from local source`() = runTest {
        val expected = DISCOUNTS
        val actual = repository.getAll()

        assertEquals(expected, actual)

        coVerify { mockLocalSource.getAll() }
    }

    @Test
    fun `insert all must insert into local source`() = runTest {
        repository.insertAll(discounts = DISCOUNTS)

        coVerify { mockLocalSource.insertAll(discounts = DISCOUNTS) }
    }

    private companion object {

        val DISCOUNTS = listOf(
            DiscountFactory.Domain.DISCOUNT
        )

    }

}