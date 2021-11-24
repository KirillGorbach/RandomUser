package com.example.randomuser.di.module

import com.example.randomuser.data.db.source.UserDBSource
import com.example.randomuser.data.db.source.UserDBSourceImpl
import com.example.randomuser.data.repository.UsersRepositoryImpl
import com.example.randomuser.data.service.source.UserServiceSource
import com.example.randomuser.data.service.source.UserServiceSourceImpl
import com.example.randomuser.domain.repository.UsersRepository
import com.example.randomuser.domain.usecase.*
import dagger.Binds
import dagger.Module


@Module
interface BindsModule {

    @Binds
    fun bindUsersRepository(getUsersRepositoryInpl: UsersRepositoryImpl): UsersRepository

    @Binds
    fun bindAllUsersUseCase(getAllUsersUseCaseImpl: AllUsersUseCaseImpl): AllUsersUseCase

    @Binds
    fun bindUserServiceSource(getUserServiceSourceImpl: UserServiceSourceImpl): UserServiceSource

    @Binds
    fun bindUsersDBRSource(getUsersDBSourceImpl: UserDBSourceImpl): UserDBSource

    @Binds
    fun getSavedUsersUseCase(getSavedUsersUseCaseImpl: SavedUsersUseCaseImpl): SavedUsersUseCase

    @Binds
    fun bindRemoveSavedUserUseCase(RemoveSavedUserUseCaseImpl: RemoveSavedUserUseCaseImpl): RemoveSavedUserUseCase

    @Binds
    fun bindInsertSavedUserUseCase(insertSavedUserUseCaseImpl: InsertSavedUserUseCaseImpl): InsertSavedUserUseCase
}