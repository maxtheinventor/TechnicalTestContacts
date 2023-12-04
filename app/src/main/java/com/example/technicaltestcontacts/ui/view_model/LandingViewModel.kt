package com.example.technicaltestcontacts.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.util.UserInfoGlobal.USER_INFO_ARRAY_LIST
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor() : ViewModel() {

    private val _closeApp = MutableLiveData<Boolean>(false)
    val closeApp: LiveData<Boolean> = _closeApp

    private val _showAppCloseWarningAlertDialog = MutableLiveData<Boolean>(false)
    val showAppCloseWarningAlertDialog: LiveData<Boolean> = _showAppCloseWarningAlertDialog

    // USER INPUT FIELDS

    //Number of contacts to search
    private val _numberOfContactsToSearch = MutableLiveData<String>("")
    val numberOfContactsToSearch: LiveData<String> = _numberOfContactsToSearch

    private val _errorInNumberOfContactsToSearch = MutableLiveData<Boolean>(false)
    val errorInNumberOfContactsToSearch: LiveData<Boolean> = _errorInNumberOfContactsToSearch

    private val _numberOfContactsToSearchError = MutableLiveData<Int>(0)
    val numberOfContactsToSearchError: LiveData<Int> = _numberOfContactsToSearchError

    //----------------------------------------------------------------------------------------------

    //Toasts

    private val _showTheFieldContainsAnErrorToast = MutableLiveData<Boolean>(false)
    val showTheFieldContainsAnErrorToast: LiveData<Boolean> = _showTheFieldContainsAnErrorToast

    private val _showNoSavedContactsToast = MutableLiveData<Boolean>(false)
    val showNoSavedContactsToast: LiveData<Boolean> = _showNoSavedContactsToast

    //----------------------------------------------------------------------------------------------

    private val _showSavedContactsPage = MutableLiveData<Boolean>(false)
    val showSavedContactsPage: LiveData<Boolean> = _showSavedContactsPage

    private val _showDownloadProgressBar = MutableLiveData<Boolean>(false)
    val showDownloadProgressBar: LiveData<Boolean> = _showDownloadProgressBar

    fun initViewSavedContacts() {

        if (USER_INFO_ARRAY_LIST.isEmpty()) {

            changeShowNoSavedContactsToastValue(true)

        } else {

            _showSavedContactsPage.value = true

        }

    }

    fun changeShowTheFieldContainsAnErrorToastValue(newValue: Boolean) {

        _showTheFieldContainsAnErrorToast.value = newValue

    }

    fun changeShowDownloadProgressBarValue(newValue: Boolean){

        _showDownloadProgressBar.value = newValue

    }

    fun initUserSearchWithNumberCriteria() {

        doUserSearchWithNumberCriteriaChecks(numberOfContactsToSearch.value.toString().trim())

    }

    fun initUserRandomSearch() {

        changeShowDownloadProgressBarValue(true)
        //TODO:Do random search

    }

    private fun doUserSearchWithNumberCriteriaChecks(numberToDoCheckOn: String) {

        changeErrorInNumberOfContactsToSearchValue(true)

        try {

            if (numberToDoCheckOn.isEmpty()) {

                changeNumberOfContactsToSearchErrorValue(newValue = R.string.fieldCantBeEmpty)
                changeShowTheFieldContainsAnErrorToastValue(true)

            } else if (numberToDoCheckOn.toInt() == 0) {

                changeNumberOfContactsToSearchErrorValue(newValue = R.string.fieldCantBeZero)
                changeShowTheFieldContainsAnErrorToastValue(true)

            } else if (numberToDoCheckOn.toInt() > 20) {

                changeNumberOfContactsToSearchErrorValue(newValue = R.string.fieldCantBeAbove20)
                changeShowTheFieldContainsAnErrorToastValue(true)

            } else {

                changeErrorInNumberOfContactsToSearchValue(false)
                changeShowDownloadProgressBarValue(true)
                //TODO: Do search

            }

        } catch (e: Exception) {

            changeNumberOfContactsToSearchErrorValue(newValue = R.string.fieldCanOnlyContainNumbers)
            changeShowTheFieldContainsAnErrorToastValue(true)

        }

    }

    fun onNumberOfContactsToSearchChange(number: String) {

        _numberOfContactsToSearch.value = number

    }

    fun changeCloseAppValue(newValue: Boolean) {

        _closeApp.value = newValue

    }

    fun changeShowNoSavedContactsToastValue(newValue: Boolean) {

        _showNoSavedContactsToast.value = newValue

    }

    fun changeErrorInNumberOfContactsToSearchValue(newValue: Boolean) {

        _errorInNumberOfContactsToSearch.value = newValue

    }

    fun changeShowAppCloseWarningAlertDialogValue(newValue: Boolean) {

        _showAppCloseWarningAlertDialog.value = newValue

    }

    private fun changeNumberOfContactsToSearchErrorValue(newValue: Int) {

        _numberOfContactsToSearchError.value = newValue

    }

}