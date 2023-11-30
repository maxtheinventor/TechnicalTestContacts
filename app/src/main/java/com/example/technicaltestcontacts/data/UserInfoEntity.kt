package com.example.technicaltestcontacts.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.technicaltestcontacts.data.interfaces.BasicUserInfo

@Entity
data class UserInfoEntity(
    @PrimaryKey
    var id:Int,
    override var firstName: String,
    override var lastName: String,
    override var age: String,
    override var gender: String,
    override var registerDate: String,
    override var phoneNumber: String,
    override var latitude: String,
    override var longitude: String
) : BasicUserInfo
