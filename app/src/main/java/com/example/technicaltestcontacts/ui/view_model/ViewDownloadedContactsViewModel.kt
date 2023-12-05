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

    // SEARCH FIELDS

    //Name

    private val _nameToSearch = MutableLiveData<String>("")
    val nameToSearch: LiveData<String> = _nameToSearch

    private val _errorInNameToSearch = MutableLiveData<Boolean>(false)
    val errorInNameToSearch: LiveData<Boolean> = _errorInNameToSearch

    private val _nameToSearchError = MutableLiveData<Int>(0)
    val nameToSearchError: LiveData<Int> = _nameToSearchError

    //Email

    private val _emailToSearch = MutableLiveData<String>("")
    val emailToSearch: LiveData<String> = _emailToSearch

    private val _errorInEmailToSearch = MutableLiveData<Boolean>(false)
    val errorInEmailToSearch: LiveData<Boolean> = _errorInEmailToSearch

    private val _emailToSearchError = MutableLiveData<Int>(0)
    val emailToSearchError: LiveData<Int> = _emailToSearchError

    //----------------------------------------------------------------------------------------------

    private val _listHasFilters = MutableLiveData<Boolean>(false)
    val listHasFilters: LiveData<Boolean> = _listHasFilters

    fun changeGoBackValue(newValue: Boolean){

        _goBack.value = newValue

    }

    fun changeShowSearchOptionsValue(){

        _showSearchOptions.value = !_showSearchOptions.value!!

    }

    fun onNameToSearchChange(name: String){

        _nameToSearch.value = name

    }

    private fun changeListHasFilters(newValue: Boolean){

        _listHasFilters.value = newValue

    }

}