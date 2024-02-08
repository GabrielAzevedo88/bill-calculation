package com.mube.taxes.data

import com.mube.taxes.data.models.TaxesEntity
import com.mube.taxes.domain.models.Taxes

internal object TaxesFactory {

    private val ID = 100
    private val NAME = "name"
    private val AMOUNT = 2f

    object Entity {
        val TAXES = TaxesEntity(
            id = ID,
            name = NAME,
            amount = AMOUNT,
            categoriesId = null
        )
    }

    object Domain {
        val TAXES = Taxes(
            id = ID,
            name = NAME,
            amount = AMOUNT,
            categoriesId = null
        )
    }

}