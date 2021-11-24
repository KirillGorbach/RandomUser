package com.example.randomuser.domain.usecase

import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.repository.UsersRepository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject


interface SavedUsersUseCase {
    operator fun invoke(): Observable<List<UserEntity>>
}
class SavedUsersUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository
) : SavedUsersUseCase{
    override fun invoke(): Observable<List<UserEntity>> {
        return usersRepository.getSavedUsers()
    }

}