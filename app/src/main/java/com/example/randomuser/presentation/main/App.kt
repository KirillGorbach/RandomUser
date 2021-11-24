package com.example.randomuser.presentation.main

import android.app.Application
import android.content.Context
import com.example.randomuser.di.AppComponent
import com.example.randomuser.di.DaggerAppComponent
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
class App : Application() {

    lateinit var appComponent: AppComponent
        private set


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .context(this.applicationContext)
            .build()
    }
}

@ExperimentalSerializationApi
val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> applicationContext.appComponent
    }