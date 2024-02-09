package com.mube.categories.data.datasources

import com.mube.categories.data.database.CategoriesDao
import com.mube.categories.data.mappers.toDomain
import com.mube.categories.data.mappers.toEntity
import com.mube.categories.domain.models.Category
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoriesLocalSource @Inject constructor(
    private val categoriesDao: CategoriesDao
) {

    fun getAll(): Flow<List<Category>> = categoriesDao.getAll().map { it.map { category -> category.toDomain() } }

    suspend fun getByName(name: String): Category? = categoriesDao.getByName(name)?.toDomain()

    suspend fun insertAll(categories: List<Category>) {
        categoriesDao.insertAll(*categories.map { it.toEntity() }.toTypedArray())
    }

    suspend fun insert(category: Category): Long = categoriesDao.insert(category.toEntity())

    suspend fun hasData(): Boolean = categoriesDao.count() > 0

}