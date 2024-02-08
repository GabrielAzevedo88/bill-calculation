package com.mube.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mube.discounts.data.database.DiscountDao
import com.mube.discounts.data.models.DiscountEntity

@Database(entities = [DiscountEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun discountDao(): DiscountDao
}