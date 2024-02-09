package com.mube.taxes.data.datasources

import com.mube.taxes.data.TaxesFactory
import com.mube.taxes.data.database.TaxesDao
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class TaxesLocalSourceTest {

    private val mockTaxesDao: TaxesDao = mockk {
        coEvery { this@mockk.getAll() } returns TAXES_ENTITIES
        coJustRun { this@mockk.insertAll(any()) }
    }

    private val localSource = TaxesLocalSource(
        taxesDao = mockTaxesDao
    )

    @Test
    fun `test get all entity models should return domain models`() = runTest {
        val expected = TAXES
        val actual = localSource.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `test inserting domain models, must save them as an entity`() = runTest {
        localSource.insertAll(TAXES)

        coVerify { mockTaxesDao.insertAll(*TAXES_ENTITIES.toTypedArray()) }
    }

    private companion object {
        private val TAXES = listOf(TaxesFactory.Domain.TAXES)
        private val TAXES_ENTITIES = listOf(TaxesFactory.Entity.TAXES)
    }

}