package com.example.technicaltestcontacts.ui.view_model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.data.network.response.random_user.ResultRandomUser
import com.example.technicaltestcontacts.util.UserInfoGlobal.DOWNLOADED_USER_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewDownloadedContactsViewModel @Inject constructor() : ViewModel() {

    private val _blurDp = MutableLiveData<Dp>(0.dp)
    val blurDp: LiveData<Dp> = _blurDp

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

    var filteredContactList: MutableList<ResultRandomUser> = arrayListOf()

    private val _showDropDownMenu = MutableLiveData<Boolean>(false)
    val showDropDownMenu: LiveData<Boolean> = _showDropDownMenu

    fun changeGoBackValue(newValue: Boolean) {

        _goBack.value = newValue

    }

    fun changeShowSearchOptionsValue() {

        _showSearchOptions.value = !_showSearchOptions.value!!

        if (!showSearchOptions.value!!) {

            changeListHasFilters(showSearchOptions.value!!)
            eliminateAllErrorsFromSearchFields()

        }

    }

    private fun eliminateAllErrorsFromSearchFields() {

        changeErrorInNameToSearchValue(false)
        changeErrorInEmailToSearchValue(false)

    }

    fun onNameToSearchChange(name: String) {

        _nameToSearch.value = name

    }

    fun doFilterSearch() {

        if (checkIfFiltersAreCorrect()) {

            changeFiltersAreCorrectValue(checkIfDownloadedContactsContainSearchedCriteria())

        }

    }

    private fun checkIfDownloadedContactsContainSearchedCriteria(): Boolean {

        var result: Boolean = false

        if (filteredContactList.isNotEmpty()) {

            filteredContactList.clear()

        }

        for (contact in DOWNLOADED_USER_DATA.body()!!.results) {

            var name = contact.name.first.toLowerCase().trim()
            var email = contact.email.toLowerCase().trim()

            var nameToSearch = nameToSearch.value!!.toLowerCase().trim()
            var emailToSearch = emailToSearch.value!!.toLowerCase().trim()

            var nameCriteria = nameToSearch.length <= name.length && name.substring(
                0,
                nameToSearch.length
            ) == nameToSearch
            var emailCriteria = emailToSearch.length <= email.length && email.substring(
                0,
                emailToSearch.length
            ) == emailToSearch

            if (nameCriteria && emailCriteria) {

                filteredContactList.add(contact)

            }

        }

        if (filteredContactList.isNotEmpty()) {

            result = true

        }

        return result

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

        if (_nameToSearch.value!!.isEmpty() && _emailToSearch.value!!.isEmpty()) {

            changeListHasFilters(false)

        }

        return result

    }

    private fun doNameToSearchFieldChecks() {

        if (nameToSearch.value!!.isNotEmpty() && nameToSearch.value!!.any { it.isDigit() }) {

            changeErrorInNameToSearchValue(true)
            changeNameToSearchErrorValue(R.string.fieldCantContainNumbers)
            changeShowFieldErrorToastValue(true)

        } else {

            changeErrorInNameToSearchValue(false)

        }

    }

    private fun doEmailToSearchFieldChecks() {

        //Maybe in the future I want to Add some criteria

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

    fun changeFiltersAreCorrectValue(newValue: Boolean) {

        _filtersAreCorrect.value = newValue

    }

    fun changeShowDropDownMenuValue() {

        if (!_showDropDownMenu.value!!) {
            changeBlurDpValue(12.dp)

        } else {
            changeBlurDpValue(0.dp)

        }

        _showDropDownMenu.value = !_showDropDownMenu.value!!

    }

    private fun changeBlurDpValue(newValue: Dp) {

        _blurDp.value = newValue

    }

    fun saveContactsInApp() {

    }

}