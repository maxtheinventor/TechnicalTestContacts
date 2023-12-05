package com.example.technicaltestcontacts.ui.compose.basics.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.ui.view_model.ViewDownloadedContactsViewModel

@Composable
fun ViewDownloadedContactsPage(
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Header(
            modifier = Modifier.align(Alignment.TopStart),
            viewDownloadedContactsViewModel = viewDownloadedContactsViewModel
        )
        Body(modifier = Modifier.align(Alignment.Center))

    }

}

@Composable
private fun Header(
    modifier: Modifier,
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {

    val showSearchOptions by viewDownloadedContactsViewModel.showSearchOptions.observeAsState()

    Column {

        TopBar(
            modifier = modifier,
            viewDownloadedContactsViewModel = viewDownloadedContactsViewModel
        )

        if (showSearchOptions!!) {

            SearchFields(
                modifier = modifier,
                viewDownloadedContactsViewModel = viewDownloadedContactsViewModel
            )

        }

    }

}

@Composable
fun TopBar(
    modifier: Modifier,
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {

    var painterResourceForSearchButton = if (viewDownloadedContactsViewModel.showSearchOptions.value!!) {

        R.drawable.close_red

    } else {

        R.drawable.contact_search_black

    }

    Row(
        modifier = modifier
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
                modifier = modifier.clickable {
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
                modifier = modifier.clickable { viewDownloadedContactsViewModel.changeShowSearchOptionsValue() }
            )

            Spacer(modifier = Modifier.size(15.dp))

            Icon(
                painter = painterResource(id = R.drawable.three_vertical_buttons_black),
                contentDescription = stringResource(id = R.string.contactsPageActions),
                tint = Color.Unspecified,
                modifier = modifier
            )

        }

    }

}

@Composable
fun SearchFields(
    modifier: Modifier,
    viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel
) {


}

@Composable
private fun Body(modifier: Modifier) {


}