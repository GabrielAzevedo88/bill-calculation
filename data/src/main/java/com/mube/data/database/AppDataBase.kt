package com.mube.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mube.categories.data.database.CategoriesDao
import com.mube.categories.data.models.CategoryEntity
import com.mube.categories.domain.models.Category
import com.mube.discounts.data.database.DiscountDao
import com.mube.discounts.data.models.DiscountEntity
import com.mube.products.data.database.ProductsDao
import com.mube.products.data.models.ProductEntity
import com.mube.taxes.data.database.TaxesDao
import com.mube.taxes.data.models.TaxesEntity

@Database(entities = [DiscountEntity::class, TaxesEntity::class, CategoryEntity::class, ProductEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun discountDao(): DiscountDao
    abstract fun taxesDao(): TaxesDao
    abstract fun categoriesDao(): CategoriesDao
    abstract fun productsDao(): ProductsDao
}
