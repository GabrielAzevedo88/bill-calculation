package com.mube.discounts.data.repository

import com.mube.discounts.data.datasources.DiscountLocalSource
import com.mube.discounts.domain.models.Discount
import com.mube.discounts.domain.repository.DiscountRepository
import javax.inject.Inject

 class DiscountRepositoryImpl @Inject constructor(
    private val localSource: DiscountLocalSource
) : DiscountRepository {

    override suspend fun getAll(): List<Discount> = localSource.getAll()

    override suspend fun insertAll(discounts: List<Discount>) = localSource.insertAll(discounts)

    override suspend fun hasData(): Boolean = localSource.hasData()

}