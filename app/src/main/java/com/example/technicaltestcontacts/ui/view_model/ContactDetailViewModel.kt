package com.example.technicaltestcontacts.ui.view_model

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.model.ContactDetailData
import com.example.technicaltestcontacts.ui.view.ContactDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactDetailViewModel @Inject constructor() : ViewModel() {

    private val _goBack = MutableLiveData<Boolean>(false)
    val goBack: LiveData<Boolean> = _goBack

    private val _userToShowInfoOf = MutableLiveData<UserInfoEntity>()
    val userToShowInfoOf: LiveData<UserInfoEntity> = _userToShowInfoOf

    private val _contactDetailDataList = MutableLiveData<MutableList<ContactDetailData>>()
    val contactDetailDataList: LiveData<MutableList<ContactDetailData>> = _contactDetailDataList

    fun changeUserToShowInfoOfValue(newValue: UserInfoEntity) {

        _userToShowInfoOf.value = null
        _userToShowInfoOf.value = newValue

    }

    fun prepareContactDetailDataList() {

        _contactDetailDataList.value = mutableListOf()

        var fullName = userToShowInfoOf.value!!.firstName + " " + userToShowInfoOf.value!!.lastName

        var genderIcon = if (userToShowInfoOf.value!!.gender.equals("female")) {
            R.drawable.female_black
        } else {
            R.drawable.male_black
        }

        var gender = userToShowInfoOf.value!!.gender.capitalize()

        _contactDetailDataList.value!!.add(
            ContactDetailData(
                infoTitle = R.string.firstAndLastName,
                infoValue = fullName,
                iconResource = R.drawable.account_black,
                showImage = false
            )
        )
        _contactDetailDataList.value!!.add(
            ContactDetailData(
                infoTitle = R.string.email,
                infoValue = userToShowInfoOf.value!!.email,
                iconResource = R.drawable.email_black,
                showImage = false
            )
        )
        _contactDetailDataList.value!!.add(
            ContactDetailData(
                infoTitle = R.string.gender,
                infoValue = gender,
                iconResource = genderIcon,
                showImage = false
            )
        )
        _contactDetailDataList.value!!.add(
            ContactDetailData(
                infoTitle = R.string.dateOfRegistration,
                infoValue = userToShowInfoOf.value!!.registerDate,
                iconResource = R.drawable.calendar_black,
                showImage = false
            )
        )
        _contactDetailDataList.value!!.add(
            ContactDetailData(
                infoTitle = R.string.telephone,
                infoValue = userToShowInfoOf.value!!.phoneNumber,
                iconResource = R.drawable.black_phone,
                showImage = false
            )
        )

        _contactDetailDataList.value!!.add(
            ContactDetailData(
                infoTitle = R.string.location,
                image = R.drawable.maps_example,
                showImage = true
            )
        )

    }

    fun changeGoBackValue(newValue: Boolean) {

        _goBack.value = newValue

    }

}