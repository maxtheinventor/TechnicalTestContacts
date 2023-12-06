package com.example.technicaltestcontacts.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.technicaltestcontacts.data.local.interfaces.BasicUserInfo
import java.io.Serializable

@Entity
data class UserInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    override var firstName: String,
    override var lastName: String,
    override var age: String,
    override var gender: String,
    override var registerDate: String,
    override var phoneNumber: String,
    override var email: String,
    override var latitude: String,
    override var longitude: String,
    override var imageLarge: String
) : BasicUserInfo, Serializable
