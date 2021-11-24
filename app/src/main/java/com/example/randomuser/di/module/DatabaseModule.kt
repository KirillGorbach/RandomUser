package com.example.randomuser.di.module

import android.content.Context
import androidx.room.Room
import com.example.randomuser.data.db.UsersDatabase
import com.example.randomuser.data.db.dao.UsersDao
import dagger.Module
import dagger.Provides


@Module
class DatabaseModule {

    @Provides
    fun providesRoomDatabase(context: Context): UsersDatabase {
        return Room.databaseBuilder(
            context,
            UsersDatabase::class.java,
            "users.db"
        ).build()
    }

    @Provides
    fun providesUsersDao(usersDatabase: UsersDatabase): UsersDao {
        return usersDatabase.getUsersDao()
    }
}