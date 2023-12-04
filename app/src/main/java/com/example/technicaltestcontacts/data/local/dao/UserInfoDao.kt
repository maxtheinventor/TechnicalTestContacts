package com.example.technicaltestcontacts.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.local.interfaces.UserInfoFunctions

@Dao
interface UserInfoDao: UserInfoFunctions {

    @Insert
    override suspend fun insertUser(userInfoEntity: UserInfoEntity):Long

    @Query("SELECT (SELECT COUNT(*) FROM UserInfoEntity) == 0")
    override suspend fun checkIfTableIsEmpty(): Boolean

    @Query("SELECT * FROM UserInfoEntity")
    override suspend fun getAllUsers(): List<UserInfoEntity>

}