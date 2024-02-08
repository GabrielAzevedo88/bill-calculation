package com.mube.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mube.discounts.data.database.DiscountDao
import com.mube.discounts.data.models.DiscountEntity
import com.mube.taxes.data.database.TaxesDao
import com.mube.taxes.data.models.TaxesEntity

@Database(entities = [DiscountEntity::class, TaxesEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun discountDao(): DiscountDao

    abstract fun taxesDao(): TaxesDao
}