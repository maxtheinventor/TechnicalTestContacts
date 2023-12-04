package com.example.technicaltestcontacts.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.technicaltestcontacts.data.local.dao.UserInfoDao
import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity

@Database(entities = [UserInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

}