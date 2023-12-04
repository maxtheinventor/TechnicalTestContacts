package com.example.technicaltestcontacts.ui.compose.basics.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import org.w3c.dom.Text

@Composable
fun ViewDownloadedContactsPage(viewDownloadedContactsViewModel: ViewDownloadedContactsViewModel) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Header()
        Body(modifier = Modifier.align(Alignment.Center))

    }

}

@Composable
private fun Header() {

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start, verticalAlignment = Alignment.CenterVertically) {

        Icon(
            painter = painterResource(id = R.drawable.arrow_back_black),
            contentDescription = stringResource(id = R.string.goBack),
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.size(15.dp))

        Text(
            text = stringResource(id = R.string.contacts_U),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.saira_extra_condensed_semi_bold))
        )



    }

}

@Composable
private fun Body(modifier: Modifier) {


}