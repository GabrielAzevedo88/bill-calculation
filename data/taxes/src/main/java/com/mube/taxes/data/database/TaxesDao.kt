package com.mube.taxes.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mube.taxes.data.models.TaxesEntity

@Dao
interface TaxesDao {

    @Query("SELECT * FROM taxes")
    fun getAll(): List<TaxesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg discounts: TaxesEntity)

}