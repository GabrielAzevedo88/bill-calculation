package com.mube.categories.data.datasources

import com.mube.categories.CategoriesFactory
import com.mube.categories.data.database.CategoriesDao
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class CategoriesLocalSourceTest {

    private val mockCategoriesDao: CategoriesDao = mockk(relaxed = true) {
        every { this@mockk.getAll() } returns CATEGORIES_ENTITIES
        justRun { this@mockk.insertAll(any()) }
    }

    private val localSource = CategoriesLocalSource(
        categoriesDao = mockCategoriesDao
    )

    @Test
    fun `test get all entity models should return domain models`() {
        val expected = CATEGORIES
        val actual = localSource.getAll()

        assertEquals(expected, actual)
    }

    @Test
    fun `test inserting domain models, must save them as an entity`() {
        localSource.insertAll(CATEGORIES)

        verify { mockCategoriesDao.insertAll(*CATEGORIES_ENTITIES.toTypedArray()) }
    }

    private companion object {
        private val CATEGORIES = listOf(CategoriesFactory.Domain.CATEGORY)
        private val CATEGORIES_ENTITIES = listOf(CategoriesFactory.Entity.CATEGORY)
    }

}