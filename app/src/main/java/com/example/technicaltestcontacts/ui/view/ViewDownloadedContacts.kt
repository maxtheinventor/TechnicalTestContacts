package com.example.technicaltestcontacts.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.technicaltestcontacts.ui.compose.basics.pages.ViewDownloadedContactsPage
import com.example.technicaltestcontacts.ui.view.ui.theme.TechnicalTestContactsTheme
import com.example.technicaltestcontacts.ui.view_model.ViewDownloadedContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ViewDownloadedContacts : ComponentActivity() {

    private val viewDownloadedContactsViewModel by viewModels<ViewDownloadedContactsViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TechnicalTestContactsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                    ViewDownloadedContactsPage(viewDownloadedContactsViewModel = viewDownloadedContactsViewModel)

                }
            }
        }
    }
}

