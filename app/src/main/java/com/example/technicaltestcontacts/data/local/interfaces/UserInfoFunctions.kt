package com.example.technicaltestcontacts.data.local.interfaces

import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity

interface UserInfoFunctions {

    suspend fun insertUser(userInfoEntity: UserInfoEntity): Long
    suspend fun checkIfTableIsEmpty(): Boolean

    suspend fun getAllUsers(): List<UserInfoEntity>

}