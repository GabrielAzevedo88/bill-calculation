package com.mube.categories.domain.repository

import com.mube.categories.domain.models.Category


interface CategoriesRepository {

    fun getAll(): List<Category>

    fun getByName(name: String): Category

    fun insertAll(categories: List<Category>)

    fun insert(category: Category): Long

}