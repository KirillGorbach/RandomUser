package com.example.randomuser.data.mapper

import com.example.randomuser.data.service.api.UserJsonData
import com.example.randomuser.presentation.main.entity.UserEntity
import javax.inject.Inject

class UserJsonDataToUserEntityMapper @Inject constructor()
    : (List<UserJsonData>) -> List<UserEntity> {
    override fun invoke(userJsonData: List<UserJsonData>):List<UserEntity> {
        return userJsonData.map {
                UserEntity(
                    saved = false,
                    id = -1,
                    firstname = it.name.first,
                    lastname = it.name.last,
                    email = it.email,
                    pictureUrl = it.picture.large
                )
            }
    }
}