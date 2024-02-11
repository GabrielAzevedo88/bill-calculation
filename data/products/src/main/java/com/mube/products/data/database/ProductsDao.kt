package com.mube.products.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mube.products.data.models.ProductEntity
import com.mube.products.domain.models.ProductId

@Dao
interface ProductsDao {

    @Query("SELECT * FROM products where category_id = :categoryId")
    suspend fun getAllByCategoryId(categoryId: Int): List<ProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg products: ProductEntity)

    @Query("SELECT COUNT(*) FROM products")
    suspend fun count(): Int

    @Query("SELECT * FROM products where id = :productId")
    suspend fun getProductById(productId: ProductId): ProductEntity?

}