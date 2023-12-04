package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class StreetRandomUser(
    @SerializedName("name")
    val name: String,
    @SerializedName("number")
    val number: Int
)
