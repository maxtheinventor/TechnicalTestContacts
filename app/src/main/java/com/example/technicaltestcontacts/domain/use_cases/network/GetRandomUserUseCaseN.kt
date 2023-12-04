package com.example.technicaltestcontacts.domain.use_cases.network

import com.example.technicaltestcontacts.data.network.response.random_user.RandomUser
import com.example.technicaltestcontacts.data.repository.network.RandomUserRepositoryN
import retrofit2.Response
import javax.inject.Inject

class GetRandomUserUseCaseN @Inject constructor(private val randomUserRepositoryN: RandomUserRepositoryN) {

    suspend operator fun invoke(): Response<RandomUser> {
        return randomUserRepositoryN.doRandomUserDownload()
    }

}