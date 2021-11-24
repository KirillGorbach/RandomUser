package com.example.randomuser.presentation.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.randomuser.R
import retrofit2.HttpException
import javax.inject.Inject

class ErrorHandler @Inject constructor(
    private val context: Context
) {

    fun createErrorToast(
        throwable: Throwable,
    ) {
        throwable.printStackTrace()
        when (throwable) {
            is HttpException -> {
                showToast(R.string.server_error)
            }
            else -> {
                showToast(R.string.connection_error)
            }
        }
    }

    private fun showToast(@StringRes textId: Int) {
        Toast.makeText(context, textId, Toast.LENGTH_SHORT).show()
    }
}