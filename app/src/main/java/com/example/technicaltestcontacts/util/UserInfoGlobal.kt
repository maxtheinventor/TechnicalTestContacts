package com.example.technicaltestcontacts.util

import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.network.response.random_user.RandomUser
import retrofit2.Response

object UserInfoGlobal {

     var SAVED_USER_INFO_ARRAY_LIST: ArrayList<UserInfoEntity> = ArrayList()
     lateinit var DOWNLOADED_USER_DATA: Response<RandomUser>
     var CONTACTS_HAVE_BEEN_SAVED: Boolean = false

}