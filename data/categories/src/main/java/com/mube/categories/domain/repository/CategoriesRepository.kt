package com.mube.categories.domain.repository

import com.mube.categories.domain.models.Category
import kotlinx.coroutines.flow.Flow


interface CategoriesRepository {

     fun getAll(): Flow<List<Category>>

    suspend fun getByName(name: String): Category?

    suspend fun insertAll(categories: List<Category>)

    suspend fun insert(category: Category): Long

    suspend fun hasData(): Boolean

}