package com.mube.billcalculation.domain.models

import com.mube.categories.domain.models.Category
import com.mube.products.domain.models.Product

data class Menu(val items: List<Pair<Category, List<Product>>>)
