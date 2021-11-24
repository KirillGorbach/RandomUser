package com.example.randomuser.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName

@Entity(tableName = "Users")
data class UserDB(
    val id: Long = 1L,
    val firstname: String,
    val lastname: String,
    @PrimaryKey
    val email: String,
    @SerialName("picture_url")
    val pictureUrl: String
)
