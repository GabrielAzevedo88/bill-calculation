package com.mube.taxes.data.repository

import com.mube.taxes.data.TaxesFactory
import com.mube.taxes.data.datasources.TaxesLocalSource
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class TaxesRepositoryImplTest {

    private val mockLocalSource: TaxesLocalSource = mockk() {
        coEvery { this@mockk.getAll() } returns TAXES
        coJustRun { this@mockk.insertAll(any()) }
    }

    private val repository = TaxesRepositoryImpl(localSource = mockLocalSource)

    @Test
    fun `get all must get from local source`() = runTest {
        val expected = TAXES
        val actual = repository.getAll()

        Assertions.assertEquals(expected, actual)

        coVerify { mockLocalSource.getAll() }
    }

    @Test
    fun `insert all must insert into local source`() = runTest {
        repository.insertAll(taxes = TAXES)

        coVerify { mockLocalSource.insertAll(taxes = TAXES) }
    }

    private companion object {

        val TAXES = listOf(
            TaxesFactory.Domain.TAXES
        )

    }

}