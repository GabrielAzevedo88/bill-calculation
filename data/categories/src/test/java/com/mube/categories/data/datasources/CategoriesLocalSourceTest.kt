package com.mube.categories.data.datasources

import com.mube.categories.CategoriesFactory
import com.mube.categories.data.database.CategoriesDao
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

internal class CategoriesLocalSourceTest {

    private val mockCategoriesDao: CategoriesDao = mockk(relaxed = true) {
        every { this@mockk.getAll() } returns flowOf(CATEGORIES_ENTITIES)
        coEvery { this@mockk.getByName(CATEGORY_NAME) } returns CATEGORY_ENTITY
        coEvery { this@mockk.insert(CATEGORY_ENTITY) } returns 100
        coJustRun { this@mockk.insertAll(any()) }
    }

    private val localSource = CategoriesLocalSource(
        categoriesDao = mockCategoriesDao
    )

    @Test
    fun `test get all entity models should return domain models`() = runTest{
        val expected = CATEGORIES
        val actual = localSource.getAll().first()

        assertEquals(expected, actual)
    }

    @Test
    fun `test get category by name`() = runTest{
        val expected = CATEGORY
        val actual = localSource.getByName(CATEGORY_NAME)

        assertEquals(expected, actual)
    }

    @Test
    fun `test inserting domain models, must save them as an entity`() = runTest {
        localSource.insertAll(CATEGORIES)

        coVerify { mockCategoriesDao.insertAll(*CATEGORIES_ENTITIES.toTypedArray()) }
    }

    @Test
    fun `test insert one category`() = runTest {
        localSource.insert(CATEGORY)

        coVerify { mockCategoriesDao.insert(CATEGORY_ENTITY) }
    }

    private companion object {
        val CATEGORY = CategoriesFactory.Domain.CATEGORY
        val CATEGORY_ENTITY = CategoriesFactory.Entity.CATEGORY
        val CATEGORIES = listOf(CategoriesFactory.Domain.CATEGORY)
        val CATEGORIES_ENTITIES = listOf(CategoriesFactory.Entity.CATEGORY)
        val CATEGORY_NAME = CATEGORY.name
    }

}