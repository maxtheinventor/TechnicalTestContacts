package com.example.technicaltestcontacts.ui.compose.basics.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.technicaltestcontacts.R


@Composable
fun ContactDetailPage() {

    Column {

        Header()

    }

}

@Composable
private fun Header() {

    Column {

        Image(
            painter = painterResource(id = R.drawable.minimal_beach),
            contentDescription = stringResource(
                id = R.string.detailContactHeaderWallpaper
            )
        )

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


}



