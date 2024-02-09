package com.mube.billcalculation.domain.usecases

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.coVerifyOrder
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test


internal class InitialDataLoadingTest {

    private val mockSetDiscountsData: SetDiscountsData = mockk(relaxUnitFun = true)
    private val mockSetTaxesData: SetTaxesData = mockk(relaxUnitFun = true)
    private val mockSetAppetizersData: SetAppetizersData = mockk(relaxUnitFun = true)
    private val mockSetMainsData: SetMainsData = mockk(relaxUnitFun = true)
    private val mockSetDrinksData: SetDrinksData = mockk(relaxUnitFun = true)
    private val mockSetAlcoholData: SetAlcoholData = mockk(relaxUnitFun = true)
    private val mockHasData: HasData = mockk {
        coEvery { this@mockk.invoke() } returns false
    }

    private val initialDataLoading = InitialDataLoading(
        setDiscountsData = mockSetDiscountsData,
        setTaxesData = mockSetTaxesData,
        setAppetizersData = mockSetAppetizersData,
        setMainsData = mockSetMainsData,
        setDrinksData = mockSetDrinksData,
        setAlcoholData = mockSetAlcoholData,
        hasData = mockHasData
    )

    @Test
    fun `tests whether use cases are called in the correct order`() = runTest {

        initialDataLoading()

        coVerifyOrder {
            mockSetAppetizersData()
            mockSetMainsData()
            mockSetDrinksData()
            mockSetAlcoholData()
            mockSetDiscountsData()
            mockSetTaxesData()
        }

    }

    @Test
    fun `if has data do not set data`() = runTest {
        coEvery { mockHasData() } returns false

        coVerify(exactly = 0) {
            mockSetAppetizersData()
            mockSetMainsData()
            mockSetDrinksData()
            mockSetAlcoholData()
            mockSetDiscountsData()
            mockSetTaxesData()
        }

    }

}