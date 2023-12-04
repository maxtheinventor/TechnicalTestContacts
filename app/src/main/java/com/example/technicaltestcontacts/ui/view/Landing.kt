package com.example.technicaltestcontacts.ui.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.technicaltestcontacts.ui.compose.basics.pages.LandingPage
import com.example.technicaltestcontacts.ui.view.ui.theme.TechnicalTestContactsTheme
import com.example.technicaltestcontacts.ui.view_model.LandingViewModel
import com.example.technicaltestcontacts.util.ToastUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Landing : ComponentActivity() {

    private val landingViewModel by viewModels<LandingViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTestContactsTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                    setupBackPressed()

                    val showSavedContactsPage by landingViewModel.showSavedContactsPage.observeAsState()
                    val goToViewDownloadedContacts by landingViewModel.goToViewDownloadedContacts.observeAsState()

                    LandingPage(landingViewModel = landingViewModel)

                    if (showSavedContactsPage!!) {

                        val intent: Intent = Intent(this, SavedContacts::class.java)
                        startActivity(intent)

                    } else if (goToViewDownloadedContacts!!) {

                        landingViewModel.changeGoToViewDownloadedContactsValue(newValue = false)
                        ToastUtils.downloadCompleted(this)
                        val intent: Intent = Intent(this, ViewDownloadedContacts::class.java)
                        startActivity(intent)

                    }
                }
            }
        }
    }

    private fun setupBackPressed() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    landingViewModel.changeShowAppCloseWarningAlertDialogValue(newValue = true)
                }
            }
        onBackPressedDispatcher.addCallback(this, callback)
    }

}