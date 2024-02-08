package com.mube.categories.domain.repository

import com.mube.categories.domain.models.Category


interface CategoriesRepository {

    fun getAll(): List<Category>

    fun insertAll(categories: List<Category>)

}