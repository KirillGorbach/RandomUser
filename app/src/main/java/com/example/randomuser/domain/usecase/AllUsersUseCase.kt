package com.example.randomuser.domain.usecase

import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.repository.UsersRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

interface AllUsersUseCase{
    operator fun invoke(): Observable<List<UserEntity>>
}

class AllUsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : AllUsersUseCase{
    override fun invoke(): Observable<List<UserEntity>> {
        return usersRepository.getUsers()
    }
}