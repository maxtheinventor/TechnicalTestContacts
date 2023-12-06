package com.example.technicaltestcontacts.ui.compose.basics.pages

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.data.network.response.random_user.ResultRandomUser
import com.example.technicaltestcontacts.ui.compose.basics.alert_dialogs.CantSaveTheSameContactsAgainWarning
import com.example.technicaltestcontacts.ui.compose.basics.texts.BasicOutlinedText
import com.example.technicaltestcontacts.ui.theme.ButtonBlue
import com.example.technicaltestcontacts.ui.view_model.DownloadedContactsViewModel
import com.example.technicaltestcontacts.util.ToastUtils
import com.example.technicaltestcontacts.util.UserInfoGlobal.DOWNLOADED_USER_DATA

@Composable
fun ViewDownloadedContactsPage(
    downloadedContactsViewModel: DownloadedContactsViewModel
) {

    val blurDp by downloadedContactsViewModel.blurDp.observeAsState()
    val showFieldErrorToast by downloadedContactsViewModel.showFieldErrorToast.observeAsState()
    val showSavedContactsSuccessfullyToast by downloadedContactsViewModel.showSavedContactsSuccessfullyToast.observeAsState()
    val showSavingContactsInAppDialog by downloadedContactsViewModel.showSavingContactsInAppDialog.observeAsState()
    val showCantCantSaveTheSameContactsAgainWarning by downloadedContactsViewModel.showCantCantSaveTheSameContactsAgainWarning.observeAsState()

    if (showFieldErrorToast!!) {

        ToastUtils.theFieldsCantContainErrors(LocalContext.current)
        downloadedContactsViewModel.changeShowFieldErrorToastValue(newValue = false)

    }

    if (showSavedContactsSuccessfullyToast!!) {

        ToastUtils.contactsSavedSuccessfully(LocalContext.current)
        downloadedContactsViewModel.changeShowSavedContactsSuccessfullyToastValue(newValue = false)

    }

    if (showCantCantSaveTheSameContactsAgainWarning!!) {

        CantSaveTheSameContactsAgainWarning(continueButton = {
            downloadedContactsViewModel.changeShowCantCantSaveTheSameContactsAgainWarningValue(
                newValue = false
            )
        })

    }

    Box(modifier = Modifier.fillMaxSize()) {

        if (showSavingContactsInAppDialog!!) {

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color.Black,
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        CircularProgressIndicator(
                            color = Color.White,
                            modifier = Modifier.size(60.dp)
                        )

                        Spacer(modifier = Modifier.size(30.dp))

                        Text(
                            text = stringResource(id = R.string.savingContactsInApp),
                            color = Color.White
                        )

                    }

                }

            }

        } else {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(blurDp!!)
            ) {

                Header(
                    downloadedContactsViewModel = downloadedContactsViewModel
                )

                Body(downloadedContactsViewModel = downloadedContactsViewModel)

            }

        }

    }

}

@Composable
private fun Header(
    downloadedContactsViewModel: DownloadedContactsViewModel
) {

    val showSearchOptions by downloadedContactsViewModel.showSearchOptions.observeAsState()

    Column {

        TopBar(
            downloadedContactsViewModel = downloadedContactsViewModel
        )

        if (showSearchOptions!!) {

            SearchFields(
                downloadedContactsViewModel = downloadedContactsViewModel,
                nameToSearchOnTextChanged = {
                    downloadedContactsViewModel.onNameToSearchChange(
                        it
                    )
                },
                emailToSearchOnTextChanged = {
                    downloadedContactsViewModel.onEmailToSearchChange(
                        it
                    )
                },
                doSearch = { downloadedContactsViewModel.doFilterSearch() }
            )

        }

    }

}

@Composable
private fun TopBar(
    downloadedContactsViewModel: DownloadedContactsViewModel
) {

    val showDropDownMenu by downloadedContactsViewModel.showDropDownMenu.observeAsState()

    var painterResourceForSearchButton =
        if (downloadedContactsViewModel.showSearchOptions.value!!) {

            R.drawable.close_red

        } else {

            R.drawable.contact_search_black

        }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Row(
            modifier = Modifier
                .weight(0.8f)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {


            Icon(
                painter = painterResource(id = R.drawable.arrow_back_black),
                contentDescription = stringResource(id = R.string.goBack),
                tint = Color.Unspecified,
                modifier = Modifier.clickable {
                    downloadedContactsViewModel.changeGoBackValue(
                        true
                    )
                }
            )

            Spacer(modifier = Modifier.size(15.dp))

            Text(
                text = stringResource(id = R.string.contacts_U),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.saira_extra_condensed_semi_bold))
            )

        }

        Row(
            modifier = Modifier
                .weight(0.2f)
                .fillMaxWidth()
        ) {

            Icon(
                painter = painterResource(id = painterResourceForSearchButton),
                contentDescription = stringResource(id = R.string.contactSearchOptions),
                tint = Color.Unspecified,
                modifier = Modifier.clickable { downloadedContactsViewModel.changeShowSearchOptionsValue() }
            )

            Spacer(modifier = Modifier.size(15.dp))

            Icon(
                painter = painterResource(id = R.drawable.three_vertical_buttons_black),
                contentDescription = stringResource(id = R.string.contactsPageActions),
                tint = Color.Unspecified,
                modifier = Modifier.clickable {
                    downloadedContactsViewModel.changeBlurDpValue()
                    downloadedContactsViewModel.changeShowDropDownMenuValue()
                }
            )

            DropdownMenu(
                expanded = showDropDownMenu!!,
                onDismissRequest = {
                    downloadedContactsViewModel.changeBlurDpValue()
                    downloadedContactsViewModel.changeShowDropDownMenuValue()
                },
            ) {

                DropdownMenuItem(
                    text = { Text(stringResource(id = R.string.saveContactsInApp)) },
                    onClick = {

                        downloadedContactsViewModel.saveContactsInApp()

                    }
                )

            }

        }

    }

}

@Composable
private fun SearchFields(
    downloadedContactsViewModel: DownloadedContactsViewModel,
    nameToSearchOnTextChanged: (String) -> Unit,
    emailToSearchOnTextChanged: (String) -> Unit,
    doSearch: () -> Unit
) {

    val nameToSearch by downloadedContactsViewModel.nameToSearch.observeAsState()
    val errorInNameToSearch by downloadedContactsViewModel.errorInNameToSearch.observeAsState()
    val nameToSearchError by downloadedContactsViewModel.nameToSearchError.observeAsState()

    val emailToSearch by downloadedContactsViewModel.emailToSearch.observeAsState()
    val errorInEmailToSearch by downloadedContactsViewModel.errorInEmailToSearch.observeAsState()
    val emailToSearchError by downloadedContactsViewModel.emailToSearchError.observeAsState()

    Column {

        Row(verticalAlignment = Alignment.CenterVertically) {

            BasicOutlinedText(
                text = nameToSearch!!,
                onTextChanged = nameToSearchOnTextChanged,
                labelText = R.string.name,
                keyboardType = KeyboardType.Text,
                mainColor = Color.Black,
                singleLine = true,
                imeAction = ImeAction.Next,
                isError = errorInNameToSearch!!,
                errorMessage = nameToSearchError!!,
                modifier = Modifier.weight(0.9f)

            )

            Icon(
                painter = painterResource(id = R.drawable.delete_black),
                contentDescription = stringResource(id = R.string.deleteFieldContent),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clickable { downloadedContactsViewModel.clearNameToSearchField() }
                    .weight(0.1f)

            )

        }

        Row(verticalAlignment = Alignment.CenterVertically) {

            BasicOutlinedText(
                text = emailToSearch!!,
                onTextChanged = emailToSearchOnTextChanged,
                labelText = R.string.email,
                keyboardType = KeyboardType.Email,
                mainColor = Color.Black,
                singleLine = true,
                imeAction = ImeAction.Done,
                isError = errorInEmailToSearch!!,
                errorMessage = emailToSearchError!!,
                modifier = Modifier.weight(0.9f)
            )

            Icon(
                painter = painterResource(id = R.drawable.delete_black),
                contentDescription = stringResource(id = R.string.deleteFieldContent),
                tint = Color.Unspecified,
                modifier = Modifier
                    .clickable { downloadedContactsViewModel.clearEmailToSearchField() }
                    .weight(0.1f)
            )

        }

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {


            Button(
                onClick = { doSearch() },
                modifier = Modifier.padding(20.dp),
                colors = ButtonDefaults.buttonColors(
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

    }

}

@Composable
private fun Body(downloadedContactsViewModel: DownloadedContactsViewModel) {

    val listHasFilters by downloadedContactsViewModel.listHasFilters.observeAsState()
    val filtersAreCorrect by downloadedContactsViewModel.filtersAreCorrect.observeAsState()

    if (listHasFilters!! && !filtersAreCorrect!!) {

        NoContactsWithThatSearchCriteria()

    } else {

        LazyColumn() {

            if (listHasFilters!! && filtersAreCorrect!!) {

                items(
                    downloadedContactsViewModel.filteredContactList,
                    key = { it.id.value }) { filteredUserData ->

                    ItemContact(
                        resultRandomUser = filteredUserData,
                        downloadedContactsViewModel = downloadedContactsViewModel
                    )

                }

            } else {

                items(DOWNLOADED_USER_DATA.body()!!.results, key = { it.id.value }) { userData ->

                    ItemContact(
                        resultRandomUser = userData,
                        downloadedContactsViewModel = downloadedContactsViewModel
                    )

                }


            }

        }

    }

}

@Composable
private fun NoContactsWithThatSearchCriteria() {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        Spacer(modifier = Modifier.size(50.dp))

        Text(
            text = stringResource(id = R.string.therAreNoContactsWithThatSearchCriteria),
            color = Color.Red, fontSize = 15.sp
        )

    }

}

@Composable
private fun ItemContact(
    resultRandomUser: ResultRandomUser,
    downloadedContactsViewModel: DownloadedContactsViewModel
) {

    var fullName = resultRandomUser.name.first + " " + resultRandomUser.name.last

    Row(
        Modifier
            .fillMaxWidth()
            .clickable {
                downloadedContactsViewModel.changeUserToShowInfoOfValue(resultRandomUser)
                downloadedContactsViewModel.changeGoToContactDetailPageValue(true) }
            .padding(
                10.dp
            ), verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(0.2f)) {

            AsyncImage(
                model = resultRandomUser.picture.large,
                placeholder = painterResource(id = R.drawable.face_black),
                error = painterResource(id = R.drawable.face_black),
                contentDescription = stringResource(id = R.string.userImage),
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp)
            )

        }

        Spacer(modifier = Modifier.size(20.dp))

        Column(modifier = Modifier.weight(0.75f)) {

            Text(
                text = fullName,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.size(10.dp))

            Text(
                text = resultRandomUser.email,
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.size(30.dp))

            Divider(modifier = Modifier.height(1.dp), color = Color(0xFFBFBFBF))

        }

        Column(modifier = Modifier.weight(0.05f)) {

            Icon(
                painter = painterResource(id = R.drawable.right_arrow_light_gray),
                contentDescription = stringResource(id = R.string.contactSearchOptions),
                tint = Color.Unspecified,
                modifier = Modifier.clickable { }
            )

        }

    }

    Spacer(modifier = Modifier.size(30.dp))

}