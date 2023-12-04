package com.example.technicaltestcontacts.data.network.client

import com.example.technicaltestcontacts.data.network.response.random_user.RandomUser
import retrofit2.Response
import retrofit2.http.GET

interface RandomUserClient {

    @GET("api/?results=10")
    suspend fun getRandomUser(): Response<RandomUser>

}