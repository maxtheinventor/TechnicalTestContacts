package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName
import java.util.Date

data class DobRandomUser(
    @SerializedName("age")
    val age: Int,
    @SerializedName("date")
    val date: Date
)
