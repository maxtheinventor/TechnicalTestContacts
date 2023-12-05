package com.example.technicaltestcontacts.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewDownloadedContactsViewModel @Inject constructor(): ViewModel() {

    private val _goBack = MutableLiveData<Boolean>(false)
    val goBack: LiveData<Boolean> = _goBack

    private val _showSearchOptions = MutableLiveData<Boolean>(false)
    val showSearchOptions: LiveData<Boolean> = _showSearchOptions

    fun changeGoBackValue(newValue: Boolean){

        _goBack.value = newValue

    }

    fun changeShowSearchOptionsValue(){

        _showSearchOptions.value = !_showSearchOptions.value!!

    }

}