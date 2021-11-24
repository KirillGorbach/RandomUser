package com.example.randomuser.domain.usecase

import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.repository.UsersRepository
import javax.inject.Inject

interface InsertSavedUserUseCase {
    operator fun invoke(userEntity: UserEntity)
}

class InsertSavedUserUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : InsertSavedUserUseCase {
    override fun invoke(userEntity: UserEntity) {
        usersRepository.insertUser(userEntity)
    }

}