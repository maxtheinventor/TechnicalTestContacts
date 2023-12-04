package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class RegisteredRandomUser(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: String
)