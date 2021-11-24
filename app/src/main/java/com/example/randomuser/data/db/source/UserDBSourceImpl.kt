package com.example.randomuser.data.db.source


import com.example.randomuser.data.db.dao.UsersDao
import com.example.randomuser.data.mapper.UserDBToEntityMapper
import com.example.randomuser.data.mapper.UserEntityToDBMapper
import com.example.randomuser.presentation.main.entity.UserEntity
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


interface UserDBSource {

    fun getSavedUsers(): Observable<List<UserEntity>>

    fun removeUser(email: String)

    fun insertUsers(usersEntity: List<UserEntity>)
}

class UserDBSourceImpl @Inject constructor(
    private val usersDao: UsersDao,
    private val userDBToEntityMapper: UserDBToEntityMapper,
    private val userEntityToDBMapper: UserEntityToDBMapper
) : UserDBSource{

    override fun getSavedUsers(): Observable<List<UserEntity>> {
        return usersDao.getSavedUsers()
            .map { it.map(userDBToEntityMapper) }
    }

    override fun removeUser(email: String) {
        usersDao.removeUser(email)
    }

    override fun insertUsers(usersEntity: List<UserEntity>) {
        usersDao.insertUsers(
            usersEntity.map(userEntityToDBMapper)
        )
    }
}