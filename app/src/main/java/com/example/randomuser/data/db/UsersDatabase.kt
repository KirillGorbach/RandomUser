package com.example.randomuser.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomuser.data.db.dao.UsersDao
import com.example.randomuser.data.db.entity.UserDB

@Database(
    entities = [
        UserDB::class
    ],
    version = 1,
    exportSchema = false
)
abstract class UsersDatabase : RoomDatabase() {

    abstract fun getUsersDao(): UsersDao
}