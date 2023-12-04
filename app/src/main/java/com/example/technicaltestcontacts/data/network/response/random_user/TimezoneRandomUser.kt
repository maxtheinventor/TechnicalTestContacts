package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class TimezoneRandomUser(
    @SerializedName("description")
    val description: String,
    @SerializedName("offset")
    val offset: String
)
