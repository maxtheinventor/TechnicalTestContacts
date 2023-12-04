package com.example.technicaltestcontacts.data.repository.network

import com.example.technicaltestcontacts.data.network.response.random_user.RandomUser
import com.example.technicaltestcontacts.data.network.service.RandomUserService
import retrofit2.Response
import javax.inject.Inject

class RandomUserRepositoryN @Inject constructor(private val api: RandomUserService) {

    suspend fun doRandomUserDownload(): Response<RandomUser> {

        return api.doRandomUserDownload()

    }

}