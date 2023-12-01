package com.example.technicaltestcontacts.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.technicaltestcontacts.data.dao.UserInfoDao
import com.example.technicaltestcontacts.data.entity.UserInfoEntity

@Database(entities = [UserInfoEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userInfoDao(): UserInfoDao

}