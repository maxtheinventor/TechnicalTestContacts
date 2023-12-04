package com.example.technicaltestcontacts.domain.use_cases.local.user_info_table

import com.example.technicaltestcontacts.data.repository.local.UserInfoRepositoryL
import javax.inject.Inject

class CheckIfUserInfoTableIsEmptyUseCaseL @Inject constructor(private val userInfoRepositoryL: UserInfoRepositoryL) {

    suspend operator fun invoke(): Boolean {
        return userInfoRepositoryL.checkIfTableIsEmpty()
    }

}