package com.mube.taxes.domain.repository

import com.mube.taxes.domain.models.Taxes

interface TaxesRepository {

    fun getAll(): List<Taxes>

    fun insertAll(taxes: List<Taxes>)

}