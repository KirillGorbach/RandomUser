package com.example.randomuser.presentation.main.entity

import javax.inject.Inject

data class UserEntity @Inject constructor(
    var saved: Boolean,
    val id: Long,
    val firstname: String,
    val lastname: String,
    val email: String,
    val pictureUrl: String
)
