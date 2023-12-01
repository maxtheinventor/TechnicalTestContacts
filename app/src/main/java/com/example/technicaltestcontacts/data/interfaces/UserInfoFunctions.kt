package com.example.technicaltestcontacts.data.interfaces

import com.example.technicaltestcontacts.data.entity.UserInfoEntity

interface UserInfoFunctions {

    suspend fun insertUser(userInfoEntity: UserInfoEntity): Long
    suspend fun checkIfTableIsEmpty(): Boolean

    suspend fun getAllUsers(): List<UserInfoEntity>

}