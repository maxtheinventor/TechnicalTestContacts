package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class IdRandomUser(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)