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
import com.example.technicaltestcontacts.ui.view_model.ViewDownloadedContactsViewModel
import com.example.technicaltestcontacts.util.ToastUtils
import com.example.technicaltestcontacts.util.UserInfoGlobal.DOWNLOADED_USER_DATA

@Composable
fun ViewDownloadedContactsPage(
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {

    val blurDp by viewDownloadedContactsViewModel.blurDp.observeAsState()
    val showFieldErrorToast by viewDownloadedContactsViewModel.showFieldErrorToast.observeAsState()
    val showSavedContactsSuccessfullyToast by viewDownloadedContactsViewModel.showSavedContactsSuccessfullyToast.observeAsState()
    val showSavingContactsInAppDialog by viewDownloadedContactsViewModel.showSavingContactsInAppDialog.observeAsState()
    val showCantCantSaveTheSameContactsAgainWarning by viewDownloadedContactsViewModel.showCantCantSaveTheSameContactsAgainWarning.observeAsState()

    if (showFieldErrorToast!!) {

        ToastUtils.theFieldsCantContainErrors(LocalContext.current)
        viewDownloadedContactsViewModel.changeShowFieldErrorToastValue(newValue = false)

    }

    if (showSavedContactsSuccessfullyToast!!) {

        ToastUtils.contactsSavedSuccessfully(LocalContext.current)
        viewDownloadedContactsViewModel.changeShowSavedContactsSuccessfullyToastValue(newValue = false)

    }

    if (showCantCantSaveTheSameContactsAgainWarning!!) {

        CantSaveTheSameContactsAgainWarning(continueButton = {
            viewDownloadedContactsViewModel.changeShowCantCantSaveTheSameContactsAgainWarningValue(
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
                    viewDownloadedContactsViewModel = viewDownloadedContactsViewModel
                )

                Body(viewDownloadedContactsViewModel = viewDownloadedContactsViewModel)

            }

        }

    }

}

@Composable
private fun Header(
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {

    val showSearchOptions by viewDownloadedContactsViewModel.showSearchOptions.observeAsState()

    Column {

        TopBar(
            viewDownloadedContactsViewModel = viewDownloadedContactsViewModel
        )

        if (showSearchOptions!!) {

            SearchFields(
                viewDownloadedContactsViewModel = viewDownloadedContactsViewModel,
                nameToSearchOnTextChanged = {
                    viewDownloadedContactsViewModel.onNameToSearchChange(
                        it
                    )
                },
                emailToSearchOnTextChanged = {
                    viewDownloadedContactsViewModel.onEmailToSearchChange(
                        it
                    )
                },
                doSearch = { viewDownloadedContactsViewModel.doFilterSearch() }
            )

        }

    }

}

@Composable
fun TopBar(
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {

    val showDropDownMenu by viewDownloadedContactsViewModel.showDropDownMenu.observeAsState()

    var painterResourceForSearchButton =
        if (viewDownloadedContactsViewModel.showSearchOptions.value!!) {

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
                    viewDownloadedContactsViewModel.changeGoBackValue(
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
                modifier = Modifier.clickable { viewDownloadedContactsViewModel.changeShowSearchOptionsValue() }
            )

            Spacer(modifier = Modifier.size(15.dp))

            Icon(
                painter = painterResource(id = R.drawable.three_vertical_buttons_black),
                contentDescription = stringResource(id = R.string.contactsPageActions),
                tint = Color.Unspecified,
                modifier = Modifier.clickable {
                    viewDownloadedContactsViewModel.changeBlurDpValue()
                    viewDownloadedContactsViewModel.changeShowDropDownMenuValue()
                }
            )

            DropdownMenu(
                expanded = showDropDownMenu!!,
                onDismissRequest = {
                    viewDownloadedContactsViewModel.changeBlurDpValue()
                    viewDownloadedContactsViewModel.changeShowDropDownMenuValue()
                },
            ) {

                DropdownMenuItem(
                    text = { Text(stringResource(id = R.string.saveContactsInApp)) },
                    onClick = {

                        viewDownloadedContactsViewModel.saveContactsInApp()

                    }
                )

            }

        }

    }

}

@Composable
fun SearchFields(
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel,
    nameToSearchOnTextChanged: (String) -> Unit,
    emailToSearchOnTextChanged: (String) -> Unit,
    doSearch: () -> Unit
) {

    val nameToSearch by viewDownloadedContactsViewModel.nameToSearch.observeAsState()
    val errorInNameToSearch by viewDownloadedContactsViewModel.errorInNameToSearch.observeAsState()
    val nameToSearchError by viewDownloadedContactsViewModel.nameToSearchError.observeAsState()

    val emailToSearch by viewDownloadedContactsViewModel.emailToSearch.observeAsState()
    val errorInEmailToSearch by viewDownloadedContactsViewModel.errorInEmailToSearch.observeAsState()
    val emailToSearchError by viewDownloadedContactsViewModel.emailToSearchError.observeAsState()

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
                    .clickable { viewDownloadedContactsViewModel.clearNameToSearchField() }
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
                    .clickable { viewDownloadedContactsViewModel.clearEmailToSearchField() }
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
private fun Body(viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel) {

    val listHasFilters by viewDownloadedContactsViewModel.listHasFilters.observeAsState()
    val filtersAreCorrect by viewDownloadedContactsViewModel.filtersAreCorrect.observeAsState()

    if (listHasFilters!! && !filtersAreCorrect!!) {

        NoContactsWithThatSearchCriteria()

    } else {

        LazyColumn() {

            if (listHasFilters!! && filtersAreCorrect!!) {

                items(
                    viewDownloadedContactsViewModel.filteredContactList,
                    key = { it.id.value }) { filteredUserData ->

                    ItemContact(filteredUserData)

                }

            } else {

                items(DOWNLOADED_USER_DATA.body()!!.results, key = { it.id.value }) { userData ->

                    ItemContact(userData)

                }


            }

        }

    }

}

@Composable
fun NoContactsWithThatSearchCriteria() {

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
fun ItemContact(resultRandomUser: ResultRandomUser) {

    Row(
        Modifier
            .fillMaxWidth()
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
                text = resultRandomUser.name.first,
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