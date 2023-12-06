package com.example.technicaltestcontacts.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.technicaltestcontacts.data.local.entity.UserInfoEntity
import com.example.technicaltestcontacts.ui.compose.basics.pages.ContactDetailPage
import com.example.technicaltestcontacts.ui.view.ui.theme.TechnicalTestContactsTheme
import com.example.technicaltestcontacts.ui.view_model.ContactDetailViewModel
import com.example.technicaltestcontacts.util.ExtraNameUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactDetail : ComponentActivity() {

    private val contactDetailViewModel by viewModels<ContactDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTestContactsTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                    initialActions()

                    ContactDetailPage(contactDetailViewModel)

                }

            }
        }
    }

    private fun initialActions(){

        getExtras()
        contactDetailViewModel.prepareContactDetailDataList()

    }

    private fun getExtras(){

        var userInfoEntity: UserInfoEntity = intent.getSerializableExtra(ExtraNameUtils.CONTACT_TO_SHOW_DETAIL) as UserInfoEntity
        contactDetailViewModel.changeUserToShowInfoOfValue(userInfoEntity)

    }

}

