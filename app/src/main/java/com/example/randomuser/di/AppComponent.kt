package com.example.randomuser.di

import android.content.Context
import com.example.randomuser.di.module.BindsModule
import com.example.randomuser.di.module.DatabaseModule
import com.example.randomuser.di.module.NetworkModule
import com.example.randomuser.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import kotlinx.serialization.ExperimentalSerializationApi


@ExperimentalSerializationApi
@Component(
    modules = [
        NetworkModule::class,
        ViewModelModule::class,
        BindsModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent : InjectFragments {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }

}