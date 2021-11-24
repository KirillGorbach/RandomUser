package com.example.randomuser.data.service.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class UserApi (

    @SerialName("results") var results : List<UserJsonData>,
    @SerialName("info") var info : Info

)

@Serializable
data class UserJsonData (

    @SerialName("name") var name : Name,
    @SerialName("email") var email : String,
    @SerialName("picture") var picture : Picture
)

@Serializable
data class Info (

    @SerialName("seed") var seed : String,
    @SerialName("results") var results : Int,
    @SerialName("page") var page : Int,
    @SerialName("version") var version : String

)

@Serializable
data class Name (

    @SerialName("title") var title : String,
    @SerialName("first") var first : String,
    @SerialName("last") var last : String

)

@Serializable
data class Picture (

    @SerialName("large") var large : String,
    @SerialName("medium") var medium : String,
    @SerialName("thumbnail") var thumbnail : String

)