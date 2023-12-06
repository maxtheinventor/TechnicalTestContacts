package com.example.technicaltestcontacts.ui.view_model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.data.network.response.random_user.ResultRandomUser
import com.example.technicaltestcontacts.domain.use_cases.local.user_info_table.InsertUserInUserInfoTableUseCaseL
import com.example.technicaltestcontacts.util.UserInfoGlobal
import com.example.technicaltestcontacts.util.UserInfoGlobal.DOWNLOADED_USER_DATA
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ViewDownloadedContactsViewModel @Inject constructor(
    private val insertUserInUserInfoTableUseCaseL: InsertUserInUserInfoTableUseCaseL
) : ViewModel() {

    private val _blurDp = MutableLiveData<Dp>(0.dp)
    val blurDp: LiveData<Dp> = _blurDp

    private val _goBack = MutableLiveData<Boolean>(false)
    val goBack: LiveData<Boolean> = _goBack

    private val _showSearchOptions = MutableLiveData<Boolean>(false)
    val showSearchOptions: LiveData<Boolean> = _showSearchOptions

    private val _listHasFilters = MutableLiveData<Boolean>(false)
    val listHasFilters: LiveData<Boolean> = _listHasFilters

    private val _filtersAreCorrect = MutableLiveData<Boolean>(false)
    val filtersAreCorrect: LiveData<Boolean> = _filtersAreCorrect

    var filteredContactList: MutableList<ResultRandomUser> = arrayListOf()

    private val _showDropDownMenu = MutableLiveData<Boolean>(false)
    val showDropDownMenu: LiveData<Boolean> = _showDropDownMenu

    private val _contactsSaved = MutableLiveData<Boolean>(false)

    private val _goToContactDetailPage = MutableLiveData<Boolean>(false)
    val goToContactDetailPage: LiveData<Boolean> = _goToContactDetailPage

    // DIALOGS

    private val _showSavingContactsInAppDialog = MutableLiveData<Boolean>(false)
    val showSavingContactsInAppDialog: LiveData<Boolean> = _showSavingContactsInAppDialog

    private val _showCantCantSaveTheSameContactsAgainWarning = MutableLiveData<Boolean>(false)
    val showCantCantSaveTheSameContactsAgainWarning: LiveData<Boolean> =
        _showCantCantSaveTheSameContactsAgainWarning

    private val _userToShowInfoOf = MutableLiveData<UserInfoEntity>()
    val userToShowInfoOf: LiveData<UserInfoEntity> = _userToShowInfoOf

    //----------------------------------------------------------------------------------------------

    // TOAST

    private val _showFieldErrorToast = MutableLiveData<Boolean>(false)
    val showFieldErrorToast: LiveData<Boolean> = _showFieldErrorToast

    private val _showSavedContactsSuccessfullyToast = MutableLiveData<Boolean>(false)
    val showSavedContactsSuccessfullyToast: LiveData<Boolean> = _showSavedContactsSuccessfullyToast

    //----------------------------------------------------------------------------------------------

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

    private fun changeFiltersAreCorrectValue(newValue: Boolean) {

        _filtersAreCorrect.value = newValue

    }

    fun changeShowDropDownMenuValue() {

        _showDropDownMenu.postValue(!_showDropDownMenu.value!!)

    }

    fun changeBlurDpValue() {

        if (!_showDropDownMenu.value!!) {

            _blurDp.postValue(12.dp)

        } else {
            _blurDp.postValue(0.dp)

        }

    }

    fun saveContactsInApp() {

        if (!_contactsSaved.value!!) {

            changeShowSavingContactsInAppDialogValue()

            viewModelScope.launch(Dispatchers.IO) {

                delay(1500)
                lateinit var userInfoEntity: UserInfoEntity

                val job = CoroutineScope(Dispatchers.IO).launch {

                    for (contact in UserInfoGlobal.DOWNLOADED_USER_DATA.body()!!.results) {

                        userInfoEntity = convertDownloadedUserToUserInfoEntity(contact = contact)

                        insertUserInUserInfoTableUseCaseL.invoke(userInfoEntity = userInfoEntity)

                    }

                }

                job.join()

                changeContactsSavedValue(newValue = true)
                changeBlurDpValue()
                changeShowDropDownMenuValue()
                changeShowSavingContactsInAppDialogValue()
                changeShowSavedContactsSuccessfullyToastValue(newValue = true)

            }

        } else {

            changeBlurDpValue()
            changeShowDropDownMenuValue()
            changeShowCantCantSaveTheSameContactsAgainWarningValue(true)

        }

    }

    private fun convertDownloadedUserToUserInfoEntity(contact: ResultRandomUser): UserInfoEntity {

        lateinit var result: UserInfoEntity

        val instant = Instant.parse(contact.registered.date)
        val dateTime = instant.atZone(ZoneId.of("UTC")).toLocalDateTime()
        val formattedDate =
            dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

        result = UserInfoEntity(
            firstName = contact.name.first,
            lastName = contact.name.last,
            age = contact.registered.age.toString(),
            gender = contact.gender,
            registerDate = formattedDate,
            phoneNumber = contact.phone,
            latitude = contact.location.coordinates.latitude,
            longitude = contact.location.coordinates.longitude,
            imageLarge = contact.picture.large
        )

        return result

    }

    private fun changeShowSavingContactsInAppDialogValue() {

        _showSavingContactsInAppDialog.postValue(!_showSavingContactsInAppDialog.value!!)

    }

    fun changeShowSavedContactsSuccessfullyToastValue(newValue: Boolean) {

        _showSavedContactsSuccessfullyToast.postValue(newValue)

    }

    private fun changeContactsSavedValue(newValue: Boolean) {

        _contactsSaved.postValue(newValue)

    }

    fun changeShowCantCantSaveTheSameContactsAgainWarningValue(newValue: Boolean) {

        _showCantCantSaveTheSameContactsAgainWarning.value = newValue

    }

    fun changeGoToContactDetailPageValue(newValue: Boolean) {

        _goToContactDetailPage.value = newValue

    }

    fun changeUserToShowInfoOfValue(newValue: ResultRandomUser) {

        _userToShowInfoOf.value = null
        _userToShowInfoOf.value = convertDownloadedUserToUserInfoEntity(newValue)

    }

}