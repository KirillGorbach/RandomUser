package com.example.randomuser.presentation.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.usecase.AllUsersUseCase
import com.example.randomuser.domain.usecase.InsertSavedUserUseCase
import com.example.randomuser.domain.usecase.RemoveSavedUserUseCase
import com.example.randomuser.presentation.util.ErrorHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class ExploreViewModel @Inject constructor(
    private val allUsersUseCase: AllUsersUseCase,
    private val insertSavedUserUseCase: InsertSavedUserUseCase,
    private val removeSavedUserUseCase: RemoveSavedUserUseCase,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    val userData: LiveData<List<UserEntity>>
        get() =_userData

    private val _userData = MutableLiveData<List<UserEntity>>()
    private val compositeDisposable = CompositeDisposable()

    init {
        getUsers()
    }

    fun getUsers() {
        compositeDisposable.add(allUsersUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { users ->
                    _userData.value = users
                },
                {
                    errorHandler.createErrorToast(it)
                }
            )
        )
    }

    fun insertSavedUser(userEntity: UserEntity) {
        Thread {
            insertSavedUserUseCase(userEntity)
        }.start()
    }

    fun removeSavedUser(userEntity: UserEntity) {
        Thread {
            removeSavedUserUseCase(userEntity)
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}