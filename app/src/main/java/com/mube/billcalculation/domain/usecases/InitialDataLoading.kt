package com.mube.billcalculation.domain.usecases

import javax.inject.Inject

internal class InitialDataLoading @Inject constructor(
    private val setDiscountsData: SetDiscountsData,
    private val setTaxesData: SetTaxesData,
    private val setAppetizersData: SetAppetizersData,
    private val setMainsData: SetMainsData,
    private val setDrinksData: SetDrinksData,
    private val setAlcoholData: SetAlcoholData
) {

    operator fun invoke() {
        setDiscountsData()
        setTaxesData()
        setAppetizersData()
        setMainsData()
        setDrinksData()
        setAlcoholData()
    }

}
