package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Order
import com.mube.billcalculation.utils.percentOf
import com.mube.taxes.domain.repository.TaxesRepository
import javax.inject.Inject

class GetTaxesValue @Inject constructor(
    private val taxesRepository: TaxesRepository
) {

    suspend operator fun invoke(items: List<Order.Item>): Float {
        val taxes = taxesRepository.getAll()
        if (items.isEmpty() || taxes.isEmpty()) return 0f

        val regularTaxes = taxes.filter { it.categoriesId == null }.sumOf { tax ->
            items.sumOf { (tax.amount percentOf it.getTotal()).toDouble() }
        }

        val taxesByCategory = taxes.filter { it.categoriesId != null }.sumOf { tax ->
            items.filter { tax.categoriesId?.contains(it.categoryId) == true }.sumOf {
                (tax.amount percentOf it.getTotal()).toDouble()
            }
        }

        return (regularTaxes + taxesByCategory).toFloat()
    }

}