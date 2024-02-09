package com.mube.taxes.domain.repository

import com.mube.taxes.domain.models.Taxes

interface TaxesRepository {

    suspend fun getAll(): List<Taxes>

    suspend fun insertAll(taxes: List<Taxes>)

    suspend fun hasData(): Boolean
}