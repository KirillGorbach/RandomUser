package com.example.randomuser.data.service

import com.example.randomuser.data.service.api.UserApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface AppService {

    @GET("?results=20&inc=name,email,picture")
    fun getUsers(): Single<UserApi>
}