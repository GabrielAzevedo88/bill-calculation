package com.mube.billcalculation.domain.usecases

import com.mube.billcalculation.domain.models.Menu
import com.mube.categories.domain.models.Category
import com.mube.categories.domain.repository.CategoriesRepository
import com.mube.products.domain.repository.ProductsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import javax.inject.Inject

internal class GetMenu @Inject constructor(
    private val categoriesRepository: CategoriesRepository,
    private val productRepository: ProductsRepository
) {

    operator fun invoke(): Flow<Menu> = categoriesRepository.getAll().map {
        val items = it.map {category ->
            val products = productRepository.getAllByCategoryId(categoryId = category.id)
            Pair(category, products)
        }
        Menu(items = items)
    }

}
