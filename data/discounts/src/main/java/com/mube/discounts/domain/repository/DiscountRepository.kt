package com.mube.discounts.domain.repository

import com.mube.discounts.domain.models.Discount

interface DiscountRepository {

    suspend fun getAll(): List<Discount>

    suspend fun insertAll(discounts: List<Discount>)

    suspend fun hasData(): Boolean
}