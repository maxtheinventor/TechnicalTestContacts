package com.example.technicaltestcontacts.data.repository

import com.example.technicaltestcontacts.data.dao.UserInfoDao
import com.example.technicaltestcontacts.data.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.interfaces.UserInfoFunctions
import javax.inject.Inject

class UserInfoRepository @Inject constructor(private val userInfoDao: UserInfoDao) :
    UserInfoFunctions {

    override suspend fun insertUser(userInfoEntity: UserInfoEntity): Long {
        return userInfoDao.insertUser(userInfoEntity)
    }

    override suspend fun checkIfTableIsEmpty(): Boolean {
        return userInfoDao.checkIfTableIsEmpty()
    }

    override suspend fun getAllUsers(): List<UserInfoEntity> {
        return userInfoDao.getAllUsers()
    }


}