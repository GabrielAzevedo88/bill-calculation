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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg discounts: CategoryEntity)

}