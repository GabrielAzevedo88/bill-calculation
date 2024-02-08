package com.mube.discounts.di

import com.mube.discounts.data.repository.DiscountRepositoryImpl
import com.mube.discounts.domain.repository.DiscountRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object DiscountModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class SingletonModule {

        @Singleton
        @Binds
        abstract fun bindDiscountRepository(repositoryImpl: DiscountRepositoryImpl): DiscountRepository

    }

}