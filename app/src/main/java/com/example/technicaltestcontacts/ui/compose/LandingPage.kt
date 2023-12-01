package com.example.technicaltestcontacts.ui.compose

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.ui.compose.alert_dialogs.AppCloseWarning
import com.example.technicaltestcontacts.ui.view_model.LandingPageViewModel

@Composable
fun LandingPage(landingPageViewModel: LandingPageViewModel) {

    val closeApp by landingPageViewModel.closeApp.observeAsState()
    val showAppCloseWarningAlertDialog by landingPageViewModel.showAppCloseWarningAlertDialog.observeAsState()

    if (closeApp!!) {

        val activity = LocalContext.current as Activity
        activity.finish()

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        if (showAppCloseWarningAlertDialog!!) {

            AppCloseWarning(closeApp = {

                landingPageViewModel.changeShowAppCloseWarningAlertDialogValue(newValue = false)
                landingPageViewModel.changeCloseAppValue(it)

            })

        }

        Header(modifier = Modifier.align(Alignment.TopEnd), closeAppIconClicked = {

            landingPageViewModel.changeShowAppCloseWarningAlertDialogValue(newValue = true)

        })

        Body()
        Footer()

    }

}

@Composable
private fun Header(modifier: Modifier, closeAppIconClicked: () -> Unit) {

    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = stringResource(id = R.string.closeApp),
        modifier = modifier.clickable { closeAppIconClicked() })

}

@Composable
private fun Body(){

}

@Composable
private fun Footer(){

}