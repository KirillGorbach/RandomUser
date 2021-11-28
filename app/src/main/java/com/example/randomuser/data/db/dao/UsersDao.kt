package com.example.randomuser.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.randomuser.data.db.entity.UserDB
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


@Dao
interface UsersDao {

    @Query("SELECT * FROM Users")
    fun getSavedUsers(): Maybe<List<UserDB>>

    @Query("DELETE FROM Users WHERE email == :email")
    fun removeUser(email: String): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUsers(users: List<UserDB>): Completable
}