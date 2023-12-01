package com.example.technicaltestcontacts.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.technicaltestcontacts.data.repository.UserInfoRepository
import com.example.technicaltestcontacts.domain.use_cases.user_info_table.CheckIfUserInfoTableIsEmptyUseCase
import com.example.technicaltestcontacts.domain.use_cases.user_info_table.GetAllUsersInUserInfoTableUseCase
import com.example.technicaltestcontacts.util.UserInfoGlobal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val checkIfUserInfoTableIsEmptyUseCase: CheckIfUserInfoTableIsEmptyUseCase,
    private val getAllUsersInUserInfoTableUseCase: GetAllUsersInUserInfoTableUseCase
) : ViewModel() {

    private val _allInitialChecksAreDone = MutableLiveData<Boolean>(false)
    val allInitialChecksAreDone: LiveData<Boolean> = _allInitialChecksAreDone

    private val _showLandingPage = MutableLiveData<Boolean>(false)
    val showLandingPage: LiveData<Boolean> = _showLandingPage

    private val isUserInfoTableEmpty = MutableLiveData<Boolean>()

    init {

        viewModelScope.launch {

            initialChecks()

        }

    }

    private suspend fun initialChecks() {

        val job = CoroutineScope(Dispatchers.IO).launch {

            isUserInfoTableEmpty.postValue(checkIfUserInfoTableIsEmptyUseCase.invoke())

        }

        job.join()


        if (!isUserInfoTableEmpty.value!!) {

            getAllUserInfoValuesAndSaveItInObject()

        }

        changeAllInitialChecksAreDoneValue(true)
        changeShowLandingPageValue(true)

    }

    private suspend fun getAllUserInfoValuesAndSaveItInObject() {

        val job = CoroutineScope(IO).launch {

            UserInfoGlobal.USER_INFO_ARRAY_LIST = getAllUsersInUserInfoTableUseCase.invoke()

        }

        job.join()

    }

    private fun changeShowLandingPageValue(newValue: Boolean) {

        _showLandingPage.value = newValue

    }

    private fun changeAllInitialChecksAreDoneValue(newValue: Boolean){

        _allInitialChecksAreDone.value = newValue

    }

}