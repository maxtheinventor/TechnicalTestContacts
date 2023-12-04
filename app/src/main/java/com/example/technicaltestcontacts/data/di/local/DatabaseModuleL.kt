package com.example.technicaltestcontacts.data.di.local

import android.content.Context
import androidx.room.Room
import com.example.technicaltestcontacts.data.local.dao.UserInfoDao
import com.example.technicaltestcontacts.data.local.database.AppDatabase
import com.example.technicaltestcontacts.util.DatabaseRelatedNames.APP_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModuleL {

    @Provides
    fun provideUserInfoDao(appDatabase: AppDatabase): UserInfoDao {
        return appDatabase.userInfoDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(appContext, AppDatabase::class.java, APP_DATABASE_NAME).build()
    }

}