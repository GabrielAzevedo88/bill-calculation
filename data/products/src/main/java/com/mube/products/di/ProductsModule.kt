package com.mube.products.di

import com.mube.products.data.repository.ProductsRepositoryImpl
import com.mube.products.domain.repository.ProductsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object ProductsModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class SingletonModule {

        @Singleton
        @Binds
        abstract fun bindRepository(repositoryImpl: ProductsRepositoryImpl): ProductsRepository

    }

}