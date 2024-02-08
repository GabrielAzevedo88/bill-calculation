package com.mube.taxes.data.datasources

import com.mube.taxes.data.TaxesFactory
import com.mube.taxes.data.database.TaxesDao
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test


internal class TaxesLocalSourceTest {

    private val mockTaxesDao: TaxesDao = mockk {
        every { this@mockk.getAll() } returns TAXES_ENTITIES
        justRun { this@mockk.insertAll(any()) }
    }

    private val localSource = TaxesLocalSource(
        taxesDao = mockTaxesDao
    )

    @Test
    fun `test get all entity models should return domain models`() {
        val expected = TAXES
        val actual = localSource.getAll()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test inserting domain models, must save them as an entity`() {
        localSource.insertAll(TAXES)

        verify { mockTaxesDao.insertAll(*TAXES_ENTITIES.toTypedArray()) }
    }

    private companion object {
        private val TAXES = listOf(TaxesFactory.Domain.TAXES)
        private val TAXES_ENTITIES = listOf(TaxesFactory.Entity.TAXES)
    }

}