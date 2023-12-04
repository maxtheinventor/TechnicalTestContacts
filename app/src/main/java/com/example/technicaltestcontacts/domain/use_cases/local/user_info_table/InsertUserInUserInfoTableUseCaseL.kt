package com.example.technicaltestcontacts.domain.use_cases.local.user_info_table

import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.repository.local.UserInfoRepositoryL
import javax.inject.Inject

class InsertUserInUserInfoTableUseCaseL @Inject constructor(private val userInfoRepositoryL: UserInfoRepositoryL) {

    suspend operator fun invoke(userInfoEntity: UserInfoEntity):Long{
        return userInfoRepositoryL.insertUser(userInfoEntity)
    }

}