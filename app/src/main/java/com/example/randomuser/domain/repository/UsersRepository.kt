package com.example.randomuser.domain.repository

import com.example.randomuser.presentation.main.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

interface UsersRepository {

    fun getUsers(): Observable<List<UserEntity>>

    fun getSavedUsers(): Maybe<List<UserEntity>>

    fun removeUser(userEntity: UserEntity): Completable

    fun insertUser(userEntity: UserEntity): Completable
}