package com.example.randomuser.domain.usecase

import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.repository.UsersRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface RemoveSavedUserUseCase {
    operator fun invoke(userEntity: UserEntity)
}

class RemoveSavedUserUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : RemoveSavedUserUseCase{
    override fun invoke(userEntity: UserEntity) {
        usersRepository.removeUser(userEntity)
    }
}