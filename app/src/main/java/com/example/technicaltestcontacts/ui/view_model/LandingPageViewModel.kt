package com.example.technicaltestcontacts.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor(): ViewModel() {

    private val _closeApp = MutableLiveData<Boolean>(false)
    val closeApp: LiveData<Boolean> = _closeApp

    private val _showAppCloseWarningAlertDialog = MutableLiveData<Boolean>(false)
    val showAppCloseWarningAlertDialog: LiveData<Boolean> = _showAppCloseWarningAlertDialog

    fun changeCloseAppValue(newValue: Boolean){

        _closeApp.value = newValue

    }

    fun changeShowAppCloseWarningAlertDialogValue(newValue: Boolean){

        _showAppCloseWarningAlertDialog.value = newValue

    }

}