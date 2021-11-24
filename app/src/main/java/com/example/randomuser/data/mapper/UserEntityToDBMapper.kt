package com.example.randomuser.data.mapper

import com.example.randomuser.data.db.entity.UserDB
import com.example.randomuser.presentation.main.entity.UserEntity
import javax.inject.Inject

class UserEntityToDBMapper @Inject constructor()
    : (UserEntity) -> UserDB{
    override fun invoke(userEntity: UserEntity): UserDB {
        return UserDB(
            id = -1,
            firstname = userEntity.firstname,
            lastname = userEntity.lastname,
            email = userEntity.email,
            pictureUrl = userEntity.pictureUrl
        )
    }
}