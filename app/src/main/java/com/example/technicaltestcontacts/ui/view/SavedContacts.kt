package com.example.technicaltestcontacts.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.technicaltestcontacts.ui.compose.basics.pages.SavedContactsPage
import com.example.technicaltestcontacts.ui.view.ui.theme.TechnicalTestContactsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedContacts : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTestContactsTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                    SavedContactsPage()

                }

            }
        }
    }
}