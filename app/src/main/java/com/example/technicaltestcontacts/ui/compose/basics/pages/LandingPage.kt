package com.example.technicaltestcontacts.ui.compose.basics.pages

import android.app.Activity
import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.ui.compose.basics.alert_dialogs.AppCloseWarning
import com.example.technicaltestcontacts.ui.compose.basics.alert_dialogs.DownloadHasFailedWarning
import com.example.technicaltestcontacts.ui.compose.basics.texts.BasicOutlinedText
import com.example.technicaltestcontacts.ui.theme.ButtonBlue
import com.example.technicaltestcontacts.ui.theme.ButtonGreen
import com.example.technicaltestcontacts.ui.view_model.LandingViewModel
import com.example.technicaltestcontacts.util.ToastUtils

@Composable
fun LandingPage(landingViewModel: LandingViewModel) {

    val showAppCloseWarningAlertDialog by landingViewModel.showAppCloseWarningAlertDialog.observeAsState()
    val showNoSavedContactsToast by landingViewModel.showNoSavedContactsToast.observeAsState()
    val showTheFieldContainsAnErrorToast by landingViewModel.showTheFieldContainsAnErrorToast.observeAsState()
    val showDownloadProgressBar by landingViewModel.showDownloadProgressBar.observeAsState()
    val downloadHasFailed by landingViewModel.downloadHasFailed.observeAsState()

    if (showNoSavedContactsToast!!) {

        ToastUtils.noContactsSavedInDbToast(LocalContext.current)
        landingViewModel.changeShowNoSavedContactsToastValue(newValue = false)

    }

    if (showTheFieldContainsAnErrorToast!!) {

        ToastUtils.theFieldContainsAnError(LocalContext.current)
        landingViewModel.changeShowTheFieldContainsAnErrorToastValue(newValue = false)

    }

    if (showAppCloseWarningAlertDialog!!) {

        AppCloseWarning(closeApp = {

            landingViewModel.changeShowAppCloseWarningAlertDialogValue(newValue = false)
            landingViewModel.changeCloseAppValue(it)

        })

    }

    if(downloadHasFailed!!){

        landingViewModel.changeShowDownloadProgressBarValue(false)
        DownloadHasFailedWarning(continueButton = {landingViewModel.changeDownloadHasFailedValue(false)})

    }

    if (showDownloadProgressBar!!) {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            CircularProgressIndicator(color = Color.Black, modifier = Modifier.size(100.dp))
            Spacer(modifier = Modifier.size(50.dp))
            Text(text = stringResource(id = R.string.downloadingContacts))

        }

    } else {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {

            Header(modifier = Modifier.align(Alignment.TopEnd), closeAppIconClicked = {

                landingViewModel.changeShowAppCloseWarningAlertDialogValue(newValue = true)

            })

            Body(
                landingViewModel = landingViewModel,
                modifier = Modifier.align(Alignment.Center)
            )

            Footer(
                landingViewModel = landingViewModel,
                modifier = Modifier.align(Alignment.BottomCenter)
            )

        }

    }

}

@Composable
private fun Header(modifier: Modifier, closeAppIconClicked: () -> Unit) {

    Icon(imageVector = Icons.Default.Close,
        contentDescription = stringResource(id = R.string.closeApp),
        modifier = modifier.clickable { closeAppIconClicked() })

}

@Composable
private fun Body(
    landingViewModel: LandingViewModel,
    modifier: Modifier
) {

    val STAR_AND_END_PADDING = 10.dp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 90.dp, start = STAR_AND_END_PADDING, end = STAR_AND_END_PADDING)
    ) {

        ContactSearch(landingViewModel = landingViewModel)
        Spacer(modifier = Modifier.size(100.dp))
        ViewSavedContacts(landingViewModel = landingViewModel)

    }

}

@Composable
private fun ContactSearch(landingViewModel: LandingViewModel) {

    val numberOfContactsToSearch by landingViewModel.numberOfContactsToSearch.observeAsState()
    val numberOfContactsToSearchHasError by landingViewModel.errorInNumberOfContactsToSearch.observeAsState()
    val numberOfContactsToSearchError by landingViewModel.numberOfContactsToSearchError.observeAsState()

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = stringResource(id = R.string.contactsSearch_U),
                fontWeight = FontWeight.ExtraBold, fontSize = 20.sp
            )

            Spacer(modifier = Modifier.size(24.dp))
            NumberOfContactsToSearch(
                numberOfContactsToSearch = numberOfContactsToSearch!!,
                numberOfContactsToSearchHasError = numberOfContactsToSearchHasError!!,
                errorMessage = numberOfContactsToSearchError!!,
                onTextChanged = {
                    landingViewModel.onNumberOfContactsToSearchChange(it)

                })

            Spacer(modifier = Modifier.size(36.dp))
            SearchButton(searchButtonClicked = { landingViewModel.initUserSearchWithNumberCriteria() })
            Spacer(modifier = Modifier.size(20.dp))
            RandomSearchButton(randomSearchButtonClicked = { landingViewModel.initUserRandomSearch() })
            Spacer(modifier = Modifier.size(24.dp))

        }

    }

}

@Composable
private fun ViewSavedContacts(
    landingViewModel: LandingViewModel
) {

    Button(
        onClick = { landingViewModel.initViewSavedContacts() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Black
        )
    ) {

        Icon(
            painter = painterResource(id = R.drawable.save_white),
            contentDescription = stringResource(id = R.string.closeApp),
            modifier = Modifier.weight(0.2f),
            tint = Color.Unspecified
        )

        Text(
            text = stringResource(id = R.string.viewSavedContacts_U),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = Color.White,
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth()
                .weight(0.8f)
        )

    }

}

@Composable
private fun Footer(landingViewModel: LandingViewModel, modifier: Modifier) {

    val closeApp by landingViewModel.closeApp.observeAsState()
    if (closeApp!!) {

        val activity = LocalContext.current as Activity
        activity.finish()

    }

    val TEXT_SIZE = 9.sp

    Column(modifier = modifier.fillMaxWidth()) {

        Divider(
            Modifier
                .padding(15.dp)
                .background(Color(0xFFF9F9F9))
                .height(0.5.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.size(12.dp))

        Row(modifier = modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

            Text(text = "App developed by ", fontSize = TEXT_SIZE)
            Text(
                text = "Max John Hindmarsh Falagan",
                fontSize = TEXT_SIZE,
                fontWeight = FontWeight.Bold
            )

        }

        Spacer(modifier = Modifier.size(24.dp))


    }

}

@Composable
private fun NumberOfContactsToSearch(
    numberOfContactsToSearch: String,
    numberOfContactsToSearchHasError: Boolean,
    errorMessage: Int,
    onTextChanged: (String) -> Unit
) {

    BasicOutlinedText(
        text = numberOfContactsToSearch,
        onTextChanged = onTextChanged,
        labelText = R.string.numContactsToSearch,
        keyboardType = KeyboardType.Number,
        mainColor = Color.Black,
        singleLine = true,
        imeAction = ImeAction.Done,
        isError = numberOfContactsToSearchHasError,
        errorMessage = errorMessage
    )

}

@Composable
private fun SearchButton(searchButtonClicked: () -> Unit) {

    Button(
        onClick = { searchButtonClicked() }, colors = ButtonDefaults.buttonColors(
            containerColor = ButtonBlue
        )
    ) {

        Text(
            text = stringResource(id = R.string.search_U),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

    }

}

@Composable
private fun RandomSearchButton(randomSearchButtonClicked: () -> Unit) {

    Button(
        onClick = { randomSearchButtonClicked() }, colors = ButtonDefaults.buttonColors(
            containerColor = ButtonGreen
        )
    ) {

        Text(
            text = stringResource(id = R.string.randomSearch_U),
            fontWeight = FontWeight.Bold,
            fontSize = 15.sp,
            color = Color.White,
            modifier = Modifier.padding(start = 20.dp, end = 20.dp)
        )

    }

}
