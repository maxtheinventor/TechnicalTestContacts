package com.example.technicaltestcontacts.ui.view_model

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicaltestcontacts.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewDownloadedContactsViewModel @Inject constructor() : ViewModel() {

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

    private val _showFieldErrorToast = MutableLiveData<Boolean>(false)
    val showFieldErrorToast: LiveData<Boolean> = _showFieldErrorToast

    private val _listHasFilters = MutableLiveData<Boolean>(false)
    val listHasFilters: LiveData<Boolean> = _listHasFilters

    private val _filtersAreCorrect = MutableLiveData<Boolean>(false)
    val filtersAreCorrect: LiveData<Boolean> = _filtersAreCorrect

    fun changeGoBackValue(newValue: Boolean) {

        _goBack.value = newValue

    }

    fun changeShowSearchOptionsValue() {

        _showSearchOptions.value = !_showSearchOptions.value!!

        if (!showSearchOptions.value!!) {

            changeListHasFilters(showSearchOptions.value!!)

        }

    }

    fun onNameToSearchChange(name: String) {

        _nameToSearch.value = name

    }

    fun doFilterSearch() {

        if (checkIfFiltersAreCorrect()) {

            checkIfDownloadedContactsContainSearchedCriteria()

        }

    }

    private fun checkIfDownloadedContactsContainSearchedCriteria(){



    }

    private fun checkIfFiltersAreCorrect(): Boolean {

        var result: Boolean = true

        doNameToSearchFieldChecks()
        doEmailToSearchFieldChecks()

        if (errorInNameToSearch.value!! || errorInEmailToSearch.value!!) {

            changeListHasFilters(false)
            result = false

        } else {

            changeListHasFilters(true)

        }

        return result

    }

    private fun doNameToSearchFieldChecks() {

        if (nameToSearch.value!!.isEmpty()) {

            changeErrorInNameToSearchValue(true)
            changeNameToSearchErrorValue(R.string.fieldCantBeEmpty)
            changeShowFieldErrorToastValue(true)

        } else {

            changeErrorInNameToSearchValue(false)

        }

    }

    private fun doEmailToSearchFieldChecks() {

        if (emailToSearch.value!!.isEmpty()) {

            changeErrorInEmailToSearchValue(true)
            changeEmailToSearchErrorValue(R.string.fieldCantBeEmpty)
            changeShowFieldErrorToastValue(true)

        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailToSearch.value!!).matches()) {

            changeErrorInEmailToSearchValue(true)
            changeEmailToSearchErrorValue(R.string.emailHasWrongFormat)
            changeShowFieldErrorToastValue(true)

        } else {

            changeErrorInEmailToSearchValue(false)

        }

    }

    fun onEmailToSearchChange(email: String) {

        _emailToSearch.value = email

    }

    private fun changeListHasFilters(newValue: Boolean) {

        _listHasFilters.value = newValue

    }

    fun clearNameToSearchField() {

        _nameToSearch.value = ""

    }

    fun clearEmailToSearchField() {

        _emailToSearch.value = ""

    }

    private fun changeErrorInNameToSearchValue(newValue: Boolean) {

        _errorInNameToSearch.value = newValue

    }

    private fun changeErrorInEmailToSearchValue(newValue: Boolean) {

        _errorInEmailToSearch.value = newValue

    }

    private fun changeNameToSearchErrorValue(newValue: Int) {

        _nameToSearchError.value = newValue

    }

    private fun changeEmailToSearchErrorValue(newValue: Int) {

        _emailToSearchError.value = newValue

    }

    fun changeShowFieldErrorToastValue(newValue: Boolean) {

        _showFieldErrorToast.value = newValue

    }

}