package com.mube.discounts.data.datasources

import com.mube.discounts.data.DiscountFactory
import com.mube.discounts.data.database.DiscountDao
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test


internal class DiscountLocalSourceTest {

    private val mockDiscountDao: DiscountDao = mockk {
        coEvery { this@mockk.getAll() } returns DISCOUNTS_ENTITIES
        coJustRun { this@mockk.insertAll(any()) }
    }

    private val localSource = DiscountLocalSource(
        discountDao = mockDiscountDao
    )

    @Test
    fun `test get all entity models should return domain models`() = runTest {
        val expected = DISCOUNTS
        val actual = localSource.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `test inserting domain models, must save them as an entity`()  = runTest{
        localSource.insertAll(DISCOUNTS)

        coVerify { mockDiscountDao.insertAll(*DISCOUNTS_ENTITIES.toTypedArray()) }
    }

    private companion object {
        private val DISCOUNTS = listOf(DiscountFactory.Domain.DISCOUNT)
        private val DISCOUNTS_ENTITIES = listOf(DiscountFactory.Entity.DISCOUNT)
    }

}