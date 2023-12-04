package com.example.technicaltestcontacts.data.repository.local

import com.example.technicaltestcontacts.data.local.dao.UserInfoDao
import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.local.interfaces.UserInfoFunctions
import javax.inject.Inject

class UserInfoRepositoryL @Inject constructor(private val userInfoDao: UserInfoDao) :
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