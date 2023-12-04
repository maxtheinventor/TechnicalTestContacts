package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class InfoRandomUser(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: Int,
    @SerializedName("seed")
    val seed: String,
    @SerializedName("version")
    val version: String
)
