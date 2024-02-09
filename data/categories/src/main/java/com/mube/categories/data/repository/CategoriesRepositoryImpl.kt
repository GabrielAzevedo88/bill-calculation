package com.mube.categories.data.repository

import com.mube.categories.data.datasources.CategoriesLocalSource
import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val localSource: CategoriesLocalSource
) : CategoriesRepository {

    override fun getAll(): List<Category> = localSource.getAll()

    override fun getByName(name: String): Category = localSource.getByName(name)

    override fun insertAll(categories: List<Category>) = localSource.insertAll(categories)

    override fun insert(category: Category): Long = localSource.insert(category)
}