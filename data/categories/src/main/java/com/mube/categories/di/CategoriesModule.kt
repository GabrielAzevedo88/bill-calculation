package com.mube.categories.di

import com.mube.categories.data.repository.CategoriesRepositoryImpl
import com.mube.categories.domain.repository.CategoriesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object CategoriesModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class SingletonModule {

        @Singleton
        @Binds
        abstract fun bindRepository(repositoryImpl: CategoriesRepositoryImpl): CategoriesRepository

    }

}