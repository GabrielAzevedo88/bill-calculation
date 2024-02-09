package com.mube.billcalculation.domain.usecases

import javax.inject.Inject

internal class InitialDataLoading @Inject constructor(
    private val setAppetizersData: SetAppetizersData,
    private val setMainsData: SetMainsData,
    private val setDrinksData: SetDrinksData,
    private val setAlcoholData: SetAlcoholData,
    private val setDiscountsData: SetDiscountsData,
    private val setTaxesData: SetTaxesData,
    private val hasData: HasData
) {

    suspend operator fun invoke() {
        if (!hasData()) {
            setAppetizersData()
            setMainsData()
            setDrinksData()
            setAlcoholData()
            setDiscountsData()
            setTaxesData()
        }
    }

}
