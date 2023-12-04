package com.example.technicaltestcontacts.data.network.service

import com.example.technicaltestcontacts.data.network.client.RandomUserClient
import com.example.technicaltestcontacts.data.network.response.random_user.RandomUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RandomUserService @Inject constructor(private val randomUserClient: RandomUserClient) {

    suspend fun doRandomUserDownload(): Response<RandomUser> {

        return withContext(Dispatchers.IO) {

            randomUserClient.getRandomUser()

        }

    }

}