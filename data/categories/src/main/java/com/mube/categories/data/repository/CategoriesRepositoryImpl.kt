package com.mube.categories.data.repository

import com.mube.categories.data.datasources.CategoriesLocalSource
import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val localSource: CategoriesLocalSource
) : CategoriesRepository {

    override fun getAll(): Flow<List<Category>> = localSource.getAll()

    override suspend fun getByName(name: String): Category? = localSource.getByName(name)

    override suspend fun insertAll(categories: List<Category>) = localSource.insertAll(categories)

    override suspend fun insert(category: Category): Long = localSource.insert(category)

    override suspend fun hasData(): Boolean = localSource.hasData()
}