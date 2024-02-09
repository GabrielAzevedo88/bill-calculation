package com.mube.categories.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mube.categories.data.models.CategoryEntity

@Dao
interface CategoriesDao {

    @Query("SELECT * FROM categories")
    fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM categories where name = :name")
    fun getByName(name: String): CategoryEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg categories: CategoryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(categoryEntity: CategoryEntity): Long

}