package com.mube.taxes.data.repository

import com.mube.taxes.data.datasources.TaxesLocalSource
import com.mube.taxes.domain.models.Taxes
import com.mube.taxes.domain.repository.TaxesRepository
import javax.inject.Inject

class TaxesRepositoryImpl @Inject constructor(
    private val localSource: TaxesLocalSource
) : TaxesRepository {

    override fun getAll(): List<Taxes> = localSource.getAll()

    override fun insertAll(taxes: List<Taxes>) = localSource.insertAll(taxes)
}