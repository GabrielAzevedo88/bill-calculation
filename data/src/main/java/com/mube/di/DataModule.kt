package com.mube.di

import android.content.Context
import androidx.room.Room
import com.mube.categories.data.database.CategoriesDao
import com.mube.data.database.AppDatabase
import com.mube.discounts.data.database.DiscountDao
import com.mube.products.data.database.ProductsDao
import com.mube.taxes.data.database.TaxesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "bill_calculation"
    ).build()

    @Provides
    fun provideDiscountDao(appDatabase: AppDatabase): DiscountDao = appDatabase.discountDao()

    @Provides
    fun provideTaxesDao(appDatabase: AppDatabase): TaxesDao = appDatabase.taxesDao()

    @Provides
    fun provideCategoriesDao(appDatabase: AppDatabase): CategoriesDao = appDatabase.categoriesDao()

    @Provides
    fun provideProductsDao(appDatabase: AppDatabase): ProductsDao = appDatabase.productsDao()

}
