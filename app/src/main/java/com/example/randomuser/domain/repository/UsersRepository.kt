package com.example.randomuser.domain.repository

import com.example.randomuser.presentation.main.entity.UserEntity
import io.reactivex.rxjava3.core.Observable

interface UsersRepository {

    fun getUsers(): Observable<List<UserEntity>>

    fun getSavedUsers(): Observable<List<UserEntity>>

    fun removeUser(userEntity: UserEntity)

    fun insertUser(userEntity: UserEntity)
}