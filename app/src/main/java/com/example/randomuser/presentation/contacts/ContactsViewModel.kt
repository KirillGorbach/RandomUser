package com.example.randomuser.presentation.contacts

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.randomuser.presentation.main.entity.UserEntity
import com.example.randomuser.domain.usecase.InsertSavedUserUseCase
import com.example.randomuser.domain.usecase.RemoveSavedUserUseCase
import com.example.randomuser.domain.usecase.SavedUsersUseCase
import com.example.randomuser.presentation.SingleLiveEvent
import com.example.randomuser.presentation.util.ErrorHandler
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class ContactsViewModel @Inject constructor(
    private val savedUsersUseCase: SavedUsersUseCase,
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
        getSavedUsers()
    }

    private fun getSavedUsers() {
        compositeDisposable.add(savedUsersUseCase()
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

    fun removeSavedUser(userEntity: UserEntity) {
        removeSavedUserUseCase(userEntity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({
                _userData.value = _userData.value?.filter { it != userEntity }
            },{
                _errorTextIdLiveData.value = errorHandler.getErrorStringIdByThrowable(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}