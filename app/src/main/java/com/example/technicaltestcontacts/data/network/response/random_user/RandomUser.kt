package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class RandomUser(
    @SerializedName("info")
    val infoRandomUser: InfoRandomUser,
    @SerializedName("results")
    val results: List<ResultRandomUser>

)

