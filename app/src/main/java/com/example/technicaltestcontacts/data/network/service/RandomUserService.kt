package com.example.technicaltestcontacts.data.network.service

import android.util.Log
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

    suspend fun doRandomUserDownloadWithQuantityCriteria(quantityCriteria: Int): Response<RandomUser>{

        return withContext(Dispatchers.IO){

            try {
                randomUserClient.getRandomUserWithQuantityCriteria(quantityCriteria = quantityCriteria)

            }catch (e: Exception){

                Log.d("Max","ZORRITAS: ${e.message}")

            } as Response<RandomUser>

        }

    }

}