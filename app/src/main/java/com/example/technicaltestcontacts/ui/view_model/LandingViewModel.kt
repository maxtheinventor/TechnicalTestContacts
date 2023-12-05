package com.example.technicaltestcontacts.ui.view_model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.data.network.response.random_user.RandomUser
import com.example.technicaltestcontacts.domain.use_cases.network.GetRandomUserUseCaseN
import com.example.technicaltestcontacts.util.UserInfoGlobal.DOWNLOADED_USER_DATA
import com.example.technicaltestcontacts.util.UserInfoGlobal.SAVED_USER_INFO_ARRAY_LIST
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(private val getRandomUserUseCaseN: GetRandomUserUseCaseN) :
    ViewModel() {

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

    private val _downloadHasFailed = MutableLiveData<Boolean>(false)
    val downloadHasFailed: LiveData<Boolean> = _downloadHasFailed

    private val _goToViewDownloadedContacts = MutableLiveData<Boolean>(false)
    val goToViewDownloadedContacts: LiveData<Boolean> = _goToViewDownloadedContacts

    fun initViewSavedContacts() {

        if (SAVED_USER_INFO_ARRAY_LIST.isEmpty()) {

            changeShowNoSavedContactsToastValue(newValue = true)

        } else {

            changeShowSavedContactsPageValue(newValue = true)

        }

    }

    fun changeShowTheFieldContainsAnErrorToastValue(newValue: Boolean) {

        _showTheFieldContainsAnErrorToast.value = newValue

    }

    fun changeShowDownloadProgressBarValue(newValue: Boolean) {

        _showDownloadProgressBar.postValue(newValue)

    }

    fun initUserSearchWithNumberCriteria() {

        doUserSearchWithNumberCriteriaChecks(numberOfContactsToSearch.value.toString().trim())

    }

    fun initUserRandomSearch() {

        viewModelScope.launch {

            changeShowDownloadProgressBarValue(true)

            val job = CoroutineScope(Dispatchers.IO).launch {
                try {

                    val response = getRandomUserUseCaseN.invoke()

                    if (response.isSuccessful) {

                        saveResponseInGlobalValue(response = response)
                        changeShowDownloadProgressBarValue(newValue = false)
                        changeShowSavedContactsPageValue(newValue = false)
                        changeGoToViewDownloadedContactsValue(newValue = true)

                    } else {

                        changeDownloadHasFailedValue(true)

                    }

                } catch (e: Exception) {

                    Log.d("Max", "Download failed")
                    changeDownloadHasFailedValue(true)

                }
            }

            job.join()

        }

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

    private fun changeErrorInNumberOfContactsToSearchValue(newValue: Boolean) {

        _errorInNumberOfContactsToSearch.value = newValue

    }

    fun changeShowAppCloseWarningAlertDialogValue(newValue: Boolean) {

        _showAppCloseWarningAlertDialog.value = newValue

    }

    private fun changeNumberOfContactsToSearchErrorValue(newValue: Int) {

        _numberOfContactsToSearchError.value = newValue

    }

    fun changeDownloadHasFailedValue(newValue: Boolean) {

        _downloadHasFailed.postValue(newValue)

    }

    private fun saveResponseInGlobalValue(response: Response<RandomUser>) {

        DOWNLOADED_USER_DATA = response

    }

    private fun changeShowSavedContactsPageValue(newValue: Boolean) {

        _showSavedContactsPage.postValue(newValue)

    }

    fun changeGoToViewDownloadedContactsValue(newValue: Boolean) {

        _goToViewDownloadedContacts.postValue(newValue)

    }

}