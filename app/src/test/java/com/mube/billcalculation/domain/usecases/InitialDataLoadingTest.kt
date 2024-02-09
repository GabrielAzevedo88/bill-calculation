package com.mube.billcalculation.domain.usecases

import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.jupiter.api.Test


internal class InitialDataLoadingTest {

    private val mockSetDiscountsData: SetDiscountsData = mockk(relaxUnitFun = true)
    private val mockSetTaxesData: SetTaxesData = mockk(relaxUnitFun = true)
    private val mockSetAppetizersData: SetAppetizersData = mockk(relaxUnitFun = true)
    private val mockSetMainsData: SetMainsData = mockk(relaxUnitFun = true)
    private val mockSetDrinksData: SetDrinksData = mockk(relaxUnitFun = true)
    private val mockSetAlcoholData: SetAlcoholData = mockk(relaxUnitFun = true)

    private val initialDataLoading = InitialDataLoading(
        setDiscountsData = mockSetDiscountsData,
        setTaxesData = mockSetTaxesData,
        setAppetizersData = mockSetAppetizersData,
        setMainsData = mockSetMainsData,
        setDrinksData = mockSetDrinksData,
        setAlcoholData = mockSetAlcoholData
    )

    @Test
    fun `tests whether use cases are called in the correct order`() {

        initialDataLoading()

        verifyOrder {
            mockSetDiscountsData()
            mockSetTaxesData()
            mockSetAppetizersData()
            mockSetMainsData()
            mockSetDrinksData()
            mockSetAlcoholData()
        }

    }

}