package com.mube.taxes.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "taxes")
data class TaxesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "amount") val amount: Float,
    @ColumnInfo(name = "categories_ids") val categoriesId: List<Int>?
)
