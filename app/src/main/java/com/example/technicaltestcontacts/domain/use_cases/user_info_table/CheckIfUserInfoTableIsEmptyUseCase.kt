package com.example.technicaltestcontacts.domain.use_cases.user_info_table

import com.example.technicaltestcontacts.data.repository.UserInfoRepository
import javax.inject.Inject

class CheckIfUserInfoTableIsEmptyUseCase @Inject constructor(private val userInfoRepository: UserInfoRepository) {

    suspend operator fun invoke(): Boolean {
        return userInfoRepository.checkIfTableIsEmpty()
    }

}