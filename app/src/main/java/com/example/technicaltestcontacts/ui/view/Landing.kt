package com.example.technicaltestcontacts.ui.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.technicaltestcontacts.ui.compose.basics.pages.LandingPage
import com.example.technicaltestcontacts.ui.view.ui.theme.TechnicalTestContactsTheme
import com.example.technicaltestcontacts.ui.view_model.LandingViewModel
import com.example.technicaltestcontacts.ui.view_model.MainActivityViewModel
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

                    val showSavedContactsPage by landingViewModel.showSavedContactsPage.observeAsState()

                    LandingPage(landingViewModel = landingViewModel)

                    if (showSavedContactsPage!!) {

                        val intent: Intent = Intent(this, SavedContacts::class.java)
                        startActivity(intent)

                    }

                }
            }
        }
    }
}