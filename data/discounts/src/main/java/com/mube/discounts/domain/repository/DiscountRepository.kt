package com.mube.discounts.domain.repository

import com.mube.discounts.domain.models.Discount

interface DiscountRepository {

    fun getAll(): List<Discount>

    fun insertAll(discounts: List<Discount>)

}