package com.example.technicaltestcontacts.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.technicaltestcontacts.ui.compose.basics.pages.ViewDownloadedContactsPage
import com.example.technicaltestcontacts.ui.view.ui.theme.TechnicalTestContactsTheme
import com.example.technicaltestcontacts.ui.view_model.DownloadedContactsViewModel
import com.example.technicaltestcontacts.util.ExtraNameUtils.Companion.CONTACT_TO_SHOW_DETAIL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DownloadedContacts : ComponentActivity() {

    private val downloadedContactsViewModel by viewModels<DownloadedContactsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTestContactsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                    val goBack by downloadedContactsViewModel.goBack.observeAsState()
                    val goToContactDetailPage by downloadedContactsViewModel.goToContactDetailPage.observeAsState()
                    val userToShowInfoOf by downloadedContactsViewModel.userToShowInfoOf.observeAsState()

                    if (goBack!!) {

                        finish()

                    }

                    ViewDownloadedContactsPage(
                        downloadedContactsViewModel = downloadedContactsViewModel
                    )

                    if (goToContactDetailPage!!) {

                        downloadedContactsViewModel.changeGoToContactDetailPageValue(newValue = false)
                        var intent: Intent = Intent(this, ContactDetail::class.java)
                        intent.putExtra(CONTACT_TO_SHOW_DETAIL, userToShowInfoOf)
                        startActivity(intent)

                    }

                }
            }
        }
    }
}

