package com.example.randomuser.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.randomuser.R
import retrofit2.HttpException
import java.net.ConnectException
import javax.inject.Inject

class ErrorHandler @Inject constructor() {

    fun getErrorStringIdByThrowable(throwable: Throwable): Int{
        return when (throwable) {
            is HttpException -> {
                if (throwable.code() == 404){
                    R.string.api_exception
                }
                else {
                    R.string.server_error
                }
            }
            is ConnectException -> {
                R.string.connection_error
            }
            else ->
                R.string.unknown_exception
        }
    }
}