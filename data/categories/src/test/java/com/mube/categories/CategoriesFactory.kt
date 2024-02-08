package com.mube.categories

import com.mube.categories.data.models.CategoryEntity
import com.mube.categories.domain.models.Category

internal object CategoriesFactory {

    private const val ID = 100
    private const val NAME = "name"

    object Entity {

        val CATEGORY = CategoryEntity(
            id = ID,
            name = NAME
        )

    }

    object Domain {

        val CATEGORY = Category(
            id = ID,
            name = NAME
        )

    }

}