package com.mube.billcalculation.di

import com.mube.billcalculation.data.repository.DraftRepositoryImpl
import com.mube.billcalculation.domain.repository.DraftRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object AppModule {

    @Module
    @InstallIn(SingletonComponent::class)
    abstract class SingletonModule {

        @Singleton
        @Binds
        abstract fun bindRepository(repositoryImpl: DraftRepositoryImpl): DraftRepository

    }

}