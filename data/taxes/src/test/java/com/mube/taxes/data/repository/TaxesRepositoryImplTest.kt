package com.mube.taxes.data.repository

import com.mube.taxes.data.TaxesFactory
import com.mube.taxes.data.datasources.TaxesLocalSource
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class TaxesRepositoryImplTest {

    private val mockLocalSource: TaxesLocalSource = mockk() {
        every { this@mockk.getAll() } returns TAXES
        justRun { this@mockk.insertAll(any()) }
    }

    private val repository = TaxesRepositoryImpl(localSource = mockLocalSource)

    @Test
    fun `get all must get from local source`() {
        val expected = TAXES
        val actual = repository.getAll()

        Assertions.assertEquals(expected, actual)

        verify { mockLocalSource.getAll() }
    }

    @Test
    fun `insert all must insert into local source`() {
        repository.insertAll(taxes = TAXES)

        verify { mockLocalSource.insertAll(taxes = TAXES) }
    }

    private companion object {

        val TAXES = listOf(
            TaxesFactory.Domain.TAXES
        )

    }

}