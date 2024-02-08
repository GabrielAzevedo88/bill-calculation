package com.mube.discounts.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mube.discounts.data.models.DiscountEntity

@Dao
interface DiscountDao {

    @Query("SELECT * FROM discounts")
    fun getAll(): List<DiscountEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg discounts: DiscountEntity)

}