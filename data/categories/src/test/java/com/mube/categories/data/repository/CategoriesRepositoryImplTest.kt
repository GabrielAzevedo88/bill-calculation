package com.mube.categories.data.repository

import com.mube.categories.CategoriesFactory
import com.mube.categories.data.datasources.CategoriesLocalSource
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CategoriesRepositoryImplTest {

    private val mockLocalSource: CategoriesLocalSource = mockk {
        coEvery { this@mockk.getAll() } returns flowOf(CATEGORIES)
        coJustRun { this@mockk.insertAll(any()) }
    }

    private val repository = CategoriesRepositoryImpl(localSource = mockLocalSource)

    @Test
    fun `get all must get from local source`() = runTest{
        val expected = CATEGORIES
        val actual = repository.getAll().first()

        assertEquals(expected, actual)

        verify { mockLocalSource.getAll() }
    }

    @Test
    fun `insert all must insert into local source`() = runTest {
        repository.insertAll(categories = CATEGORIES)

        coVerify { mockLocalSource.insertAll(categories = CATEGORIES) }
    }

    private companion object {
        val CATEGORIES = listOf(CategoriesFactory.Domain.CATEGORY)
    }

}