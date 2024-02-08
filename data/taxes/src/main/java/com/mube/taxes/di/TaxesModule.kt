package com.mube.taxes.di

import com.mube.taxes.data.repository.TaxesRepositoryImpl
import com.mube.taxes.domain.repository.TaxesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object TaxesModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class SingletonModule {

        @Singleton
        @Binds
        abstract fun bindTaxesRepository(repositoryImpl: TaxesRepositoryImpl): TaxesRepository

    }

}