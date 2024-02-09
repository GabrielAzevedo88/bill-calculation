package com.mube.taxes.data.datasources

import com.mube.taxes.data.database.TaxesDao
import com.mube.taxes.data.mappers.toDomain
import com.mube.taxes.data.mappers.toEntity
import com.mube.taxes.domain.models.Taxes
import javax.inject.Inject

class TaxesLocalSource @Inject constructor(
    private val taxesDao: TaxesDao
) {

    suspend fun getAll(): List<Taxes> = taxesDao.getAll().map { it.toDomain() }

    suspend fun insertAll(taxes: List<Taxes>) {
        taxesDao.insertAll(*taxes.map { it.toEntity() }.toTypedArray())
    }

    suspend fun hasData(): Boolean = taxesDao.count() > 0

}