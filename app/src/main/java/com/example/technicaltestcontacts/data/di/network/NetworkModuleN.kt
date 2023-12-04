package com.example.technicaltestcontacts.data.di.network

import com.example.technicaltestcontacts.data.network.client.RandomUserClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModuleN {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideRandomUserClient(retrofit: Retrofit):RandomUserClient{
        return retrofit.create(RandomUserClient::class.java)
    }


}