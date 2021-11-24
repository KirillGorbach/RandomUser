package com.example.randomuser.data.service.source

import android.util.Log
import com.example.randomuser.data.mapper.UserJsonDataToUserEntityMapper
import com.example.randomuser.data.service.AppService
import com.example.randomuser.presentation.main.entity.UserEntity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.toObservable
import javax.inject.Inject


interface UserServiceSource {
    fun getUsers(): Observable<List<UserEntity>>
}

class UserServiceSourceImpl  @Inject constructor(
    private val appService: AppService,
    private val userJsonDataToUserEntityMapper: UserJsonDataToUserEntityMapper
) : UserServiceSource{

    override fun getUsers(): Observable<List<UserEntity>> {
        return appService.getUsers()
            .map { it.results }
            .map { userJsonDataToUserEntityMapper.invoke(it) }
            .toObservable()
    }
}