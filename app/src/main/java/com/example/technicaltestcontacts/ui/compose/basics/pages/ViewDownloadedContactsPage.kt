package com.example.technicaltestcontacts.ui.compose.basics.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.data.network.response.random_user.ResultRandomUser
import com.example.technicaltestcontacts.ui.compose.basics.texts.BasicOutlinedText
import com.example.technicaltestcontacts.ui.theme.ButtonBlue
import com.example.technicaltestcontacts.ui.view_model.ViewDownloadedContactsViewModel
import com.example.technicaltestcontacts.util.UserInfoGlobal.DOWNLOADED_USER_DATA

@Composable
fun ViewDownloadedContactsPage(
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Header(
            viewDownloadedContactsViewModel = viewDownloadedContactsViewModel
        )

        Body()

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
                emailToSearchOnTextChanged = {},
                doSearch = {}
            )

        }

    }

}

@Composable
fun TopBar(
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {

    var painterResourceForSearchButton =
        if (viewDownloadedContactsViewModel.showSearchOptions.value!!) {

            R.drawable.close_red

        } else {

            R.drawable.contact_search_black

        }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
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
                modifier = Modifier
            )

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

        BasicOutlinedText(
            text = nameToSearch!!,
            onTextChanged = nameToSearchOnTextChanged,
            labelText = R.string.name,
            keyboardType = KeyboardType.Text,
            mainColor = Color.Black,
            singleLine = true,
            imeAction = ImeAction.Next,
            isError = errorInNameToSearch!!,
            errorMessage = nameToSearchError!!
        )

        BasicOutlinedText(
            text = emailToSearch!!,
            onTextChanged = emailToSearchOnTextChanged,
            labelText = R.string.email,
            keyboardType = KeyboardType.Email,
            mainColor = Color.Black,
            singleLine = true,
            imeAction = ImeAction.Done,
            isError = errorInEmailToSearch!!,
            errorMessage = emailToSearchError!!
        )

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
private fun Body() {

    LazyColumn() {

        items(DOWNLOADED_USER_DATA.body()!!.results, key = { it.id.value }) { userData ->

            ItemContact(userData)

        }

    }


}

@Composable
fun ItemContact(resultRandomUser: ResultRandomUser) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = resultRandomUser.name.first, modifier = Modifier.weight(1f)
            )

        }

    }

}