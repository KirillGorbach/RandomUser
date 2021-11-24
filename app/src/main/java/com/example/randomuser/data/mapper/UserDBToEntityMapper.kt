package com.example.randomuser.data.mapper

import com.example.randomuser.data.db.entity.UserDB
import com.example.randomuser.presentation.main.entity.UserEntity
import javax.inject.Inject

class UserDBToEntityMapper @Inject constructor()
    :(UserDB) -> UserEntity {
    override fun invoke(userDB: UserDB): UserEntity {
        return UserEntity(
            saved = true,
            id = userDB.id,
            firstname = userDB.firstname,
            lastname = userDB.lastname,
            email = userDB.email,
            pictureUrl = userDB.pictureUrl
        )
    }
}