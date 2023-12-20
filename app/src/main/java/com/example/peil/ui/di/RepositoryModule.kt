package com.example.peil.ui.di

import com.example.peil.ui.screens.create_account.data.CreateAccountRepository
import com.example.peil.ui.screens.create_account.data.CreateAccountRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindCreateAccountRepository(repository: CreateAccountRepositoryImpl): CreateAccountRepository
}