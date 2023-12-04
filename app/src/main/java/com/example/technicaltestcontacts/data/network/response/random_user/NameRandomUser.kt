package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class NameRandomUser(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,
    @SerializedName("title")
    val title: String
)
