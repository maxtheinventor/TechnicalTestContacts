package com.example.technicaltestcontacts.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.model.ContactDetailData
import com.example.technicaltestcontacts.ui.view.ContactDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor() : ViewModel() {

    private val _userToShowInfoOf = MutableLiveData<UserInfoEntity>()
    val userToShowInfoOf: LiveData<UserInfoEntity> = _userToShowInfoOf

    private val _contactDetailDataList = MutableLiveData<ContactDetailData>()
    val contactDetailDataList: LiveData<ContactDetailData> = _contactDetailDataList

    fun changeUserToShowInfoOfValue(newValue: UserInfoEntity) {

        _userToShowInfoOf.value = null
        _userToShowInfoOf.value = newValue

    }

    fun prepareContactDetailDataList() {


    }

}