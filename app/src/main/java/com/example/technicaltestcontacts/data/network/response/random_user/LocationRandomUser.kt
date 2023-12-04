package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName

data class LocationRandomUser(
    @SerializedName("city")
    val city: String,
    @SerializedName("coordinates")
    val coordinates: CoordinatesRandomUser,
    @SerializedName("country")
    val country: String,
    @SerializedName("postcode")
    val postcode: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("street")
    val street: StreetRandomUser,
    @SerializedName("timezone")
    val timezone: TimezoneRandomUser
)
