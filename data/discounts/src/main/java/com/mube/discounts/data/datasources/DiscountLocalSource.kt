package com.mube.discounts.data.datasources

import com.mube.discounts.data.database.DiscountDao
import com.mube.discounts.data.mappers.toDomain
import com.mube.discounts.data.mappers.toEntity
import com.mube.discounts.domain.models.Discount
import javax.inject.Inject

class DiscountLocalSource @Inject constructor(
    private val discountDao: DiscountDao
) {

    suspend fun getAll(): List<Discount> = discountDao.getAll().map { it.toDomain() }

    suspend fun insertAll(discounts: List<Discount>) {
        discountDao.insertAll(*discounts.map { it.toEntity() }.toTypedArray())
    }

   suspend fun hasData(): Boolean = discountDao.count() > 0

}