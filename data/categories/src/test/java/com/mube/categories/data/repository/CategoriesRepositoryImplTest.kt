package com.mube.categories.data.repository

import com.mube.categories.CategoriesFactory
import com.mube.categories.data.datasources.CategoriesLocalSource
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CategoriesRepositoryImplTest {

    private val mockLocalSource: CategoriesLocalSource = mockk() {
        every { this@mockk.getAll() } returns CATEGORIES
        justRun { this@mockk.insertAll(any()) }
    }

    private val repository = CategoriesRepositoryImpl(localSource = mockLocalSource)

    @Test
    fun `get all must get from local source`() {
        val expected = CATEGORIES
        val actual = repository.getAll()

        assertEquals(expected, actual)

        verify { mockLocalSource.getAll() }
    }

    @Test
    fun `insert all must insert into local source`() {
        repository.insertAll(categories = CATEGORIES)

        verify { mockLocalSource.insertAll(categories = CATEGORIES) }
    }

    private companion object {
        val CATEGORIES = listOf(CategoriesFactory.Domain.CATEGORY)
    }

}