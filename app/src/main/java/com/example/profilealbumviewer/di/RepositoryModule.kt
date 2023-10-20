package com.example.profilealbumviewer.di

import com.example.profilealbumviewer.data.repository.UserDataRepository
import com.example.profilealbumviewer.data.repository.UserDataRepositoryImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindProductsRepository(
        productsRepositoryImpl: UserDataRepositoryImplementation
    ): UserDataRepository

}