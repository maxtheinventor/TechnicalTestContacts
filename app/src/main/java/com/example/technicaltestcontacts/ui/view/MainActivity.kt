package com.example.technicaltestcontacts.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.technicaltestcontacts.ui.compose.LandingPage
import com.example.technicaltestcontacts.ui.theme.TechnicalTestContactsTheme
import com.example.technicaltestcontacts.ui.view_model.LandingPageViewModel
import com.example.technicaltestcontacts.ui.view_model.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainActivityViewModel by viewModels<MainActivityViewModel>()
    private val landingPageViewModel by viewModels<LandingPageViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                mainActivityViewModel.allInitialChecksAreDone.value == false
            }
        }

        setContent {
            TechnicalTestContactsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {

                    val showLandingPage by mainActivityViewModel.showLandingPage.observeAsState()

                    if (showLandingPage!!) {

                        LandingPage(landingPageViewModel)

                    }

                }
            }
        }
    }
}

