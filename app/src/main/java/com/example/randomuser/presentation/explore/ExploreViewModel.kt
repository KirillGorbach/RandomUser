package com.example.randomuser.presentation.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.usecase.AllUsersUseCase
import com.example.randomuser.domain.usecase.InsertSavedUserUseCase
import com.example.randomuser.domain.usecase.RemoveSavedUserUseCase
import com.example.randomuser.presentation.SingleLiveEvent
import com.example.randomuser.presentation.util.ErrorHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
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
    val errorTextIdLiveData: LiveData<Int>
        get() = _errorTextIdLiveData
    private val _errorTextIdLiveData = SingleLiveEvent<Int>()
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
                    _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
                }
            )
        )
    }

    fun insertSavedUser(userEntity: UserEntity) {
        insertSavedUserUseCase(userEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({},{
                _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
            })
    }

    fun removeSavedUser(userEntity: UserEntity) {
        removeSavedUserUseCase(userEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({},{
                _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}