package com.example.randomuser.data.db.source


import com.example.randomuser.data.db.dao.UsersDao
import com.example.randomuser.data.mapper.UserDBToEntityMapper
import com.example.randomuser.data.mapper.UserEntityToDBMapper
import com.example.randomuser.presentation.main.entity.UserEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import java.lang.Exception
import javax.inject.Inject


interface UserDBSource {

    fun getSavedUsers(): Maybe<List<UserEntity>>

    fun removeUser(email: String): Completable

    fun insertUsers(usersEntity: List<UserEntity>): Completable
}

class UserDBSourceImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val userDBToEntityMapper: UserDBToEntityMapper,
    private val userEntityToDBMapper: UserEntityToDBMapper
) : UserDBSource{

    override fun getSavedUsers(): Maybe<List<UserEntity>> {
        return usersDao.getSavedUsers()
            .map { it.map(userDBToEntityMapper) }
    }

    override fun removeUser(email: String): Completable {
        return usersDao.removeUser(email)
    }

    override fun insertUsers(usersEntity: List<UserEntity>): Completable {
        return usersDao.insertUsers(
            usersEntity.map(userEntityToDBMapper)
        )
    }
}