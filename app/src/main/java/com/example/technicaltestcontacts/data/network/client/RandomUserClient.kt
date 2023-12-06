package com.example.technicaltestcontacts.data.network.client

import com.example.technicaltestcontacts.data.network.response.random_user.RandomUser
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandomUserClient {

    @GET("api/?results=10")
    suspend fun getRandomUser(): Response<RandomUser>

    @GET("api")
    suspend fun getRandomUserWithQuantityCriteria(@Query(value = "results") quantityCriteria:Int): Response<RandomUser>

}