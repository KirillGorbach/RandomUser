package com.example.randomuser.data.repository

import com.example.randomuser.data.db.source.UserDBSource
import com.example.randomuser.data.service.source.UserServiceSource
import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.repository.UsersRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val userServiceSource: UserServiceSource,
    private val userDBSource: UserDBSource
) : UsersRepository{
    override fun getUsers(): Observable<List<UserEntity>> {
        return userServiceSource.getUsers()
    }

    override fun getSavedUsers(): Maybe<List<UserEntity>> {
        return userDBSource.getSavedUsers()
    }

    override fun removeUser(userEntity: UserEntity): Completable {
        return userDBSource.removeUser(userEntity.email)
    }

    override fun insertUser(userEntity: UserEntity): Completable {
        return userDBSource.insertUsers(listOf(userEntity))
    }

}