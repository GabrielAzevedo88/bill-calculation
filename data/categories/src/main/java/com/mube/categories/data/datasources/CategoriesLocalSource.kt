package com.mube.categories.data.datasources

import com.mube.categories.data.database.CategoriesDao
import com.mube.categories.data.mappers.toDomain
import com.mube.categories.data.mappers.toEntity
import com.mube.categories.domain.models.Category
import javax.inject.Inject

class CategoriesLocalSource @Inject constructor(
    private val categoriesDao: CategoriesDao
) {

    fun getAll(): List<Category> = categoriesDao.getAll().map { it.toDomain() }

    fun insertAll(categories: List<Category>) {
        categoriesDao.insertAll(*categories.map { it.toEntity() }.toTypedArray())
    }

}