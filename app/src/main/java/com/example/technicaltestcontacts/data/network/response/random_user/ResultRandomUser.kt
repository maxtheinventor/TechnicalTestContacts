package com.example.technicaltestcontacts.data.network.response.random_user

import com.google.gson.annotations.SerializedName
import java.util.jar.Attributes.Name

data class ResultRandomUser(
    @SerializedName("cell")
    val cell: String,
    @SerializedName("dob")
    val dob: DobRandomUser,
    @SerializedName("email")
    val email: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("id")
    val id: IdRandomUser,
    @SerializedName("location")
    val location: LocationRandomUser,
    @SerializedName("login")
    val login: LoginRandomUser,
    @SerializedName("name")
    val name: NameRandomUser,
    @SerializedName("nat")
    val nat: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("picture")
    val picture: PictureRandomUser,
    @SerializedName("registered")
    val registered: RegisteredRandomUser
)
