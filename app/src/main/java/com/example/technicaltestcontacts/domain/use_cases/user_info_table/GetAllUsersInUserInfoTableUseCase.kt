package com.example.technicaltestcontacts.domain.use_cases.user_info_table

import com.example.technicaltestcontacts.data.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.repository.UserInfoRepository
import javax.inject.Inject

class GetAllUsersInUserInfoTableUseCase @Inject constructor(private val userInfoRepository: UserInfoRepository) {

    suspend operator fun invoke(): ArrayList<UserInfoEntity> {
        return userInfoRepository.getAllUsers() as ArrayList<UserInfoEntity>
    }

}