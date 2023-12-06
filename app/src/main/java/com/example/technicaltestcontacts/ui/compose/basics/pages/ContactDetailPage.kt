package com.example.technicaltestcontacts.ui.compose.basics.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.example.technicaltestcontacts.R
import com.example.technicaltestcontacts.data.model.ContactDetailData
import com.example.technicaltestcontacts.ui.view_model.ContactDetailViewModel

@Composable
fun ContactDetailPage(contactDetailViewModel: ContactDetailViewModel) {

    Column() {

        Header(contactDetailViewModel = contactDetailViewModel)
        Body(contactDetailViewModel = contactDetailViewModel)

    }

}

@Composable
private fun Header(contactDetailViewModel: ContactDetailViewModel) {

    ConstraintLayout(Modifier) {

        val (backgroundImage, userImage, headerInfoAndButtons, cameraIcon, editIcon, contactDetailList) = createRefs()

        val topGuideAsyncImage = createGuidelineFromTop(0.75f)
        val startGuideAsyncImage = createGuidelineFromStart(0.05f)

        val startGuideCameraIcon = createGuidelineFromStart(0.74f)
        val startGuideEditIcon = createGuidelineFromStart(0.87f)

        val topGuideForHeaderInfoAndButtons = createGuidelineFromTop(0.02f)
        val startGuideForHeaderInfoAndButtons = createGuidelineFromStart(0.02f)

        Image(
            modifier = Modifier.constrainAs(backgroundImage) {
            },
            painter = painterResource(id = R.drawable.minimal_beach),
            contentDescription = stringResource(
                id = R.string.detailContactHeaderWallpaper
            )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .constrainAs(headerInfoAndButtons) {

                    top.linkTo(topGuideForHeaderInfoAndButtons)
                    start.linkTo(startGuideForHeaderInfoAndButtons)

                },
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
                        contactDetailViewModel.changeGoBackValue(true)
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

                Spacer(modifier = Modifier.size(15.dp))

                Icon(
                    painter = painterResource(id = R.drawable.three_vertical_buttons_black),
                    contentDescription = stringResource(id = R.string.contactDetailsPageActions),
                    tint = Color.Unspecified,
                    modifier = Modifier.clickable {

                    }
                )

            }

        }


        AsyncImage(
            model = contactDetailViewModel.userToShowInfoOf.value!!.imageLarge,
            placeholder = painterResource(id = R.drawable.face_black),
            error = painterResource(id = R.drawable.face_black),
            contentDescription = stringResource(id = R.string.userImage),
            modifier = Modifier
                .clip(CircleShape)
                .border(4.dp, Color.White, CircleShape)
                .size(80.dp)
                .constrainAs(userImage) {

                    top.linkTo(topGuideAsyncImage)
                    start.linkTo(startGuideAsyncImage)

                }
        )

        Icon(
            painter = painterResource(id = R.drawable.camera_black),
            contentDescription = stringResource(id = R.string.cameraIconContactDetailsPage),
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable {

                }
                .constrainAs(cameraIcon) {

                    top.linkTo(backgroundImage.bottom)
                    start.linkTo(startGuideCameraIcon)

                }
        )

        Icon(
            painter = painterResource(id = R.drawable.pencil_black),
            contentDescription = stringResource(id = R.string.editIconContactDetailsPage),
            tint = Color.Unspecified,
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable {

                }
                .constrainAs(editIcon) {

                    top.linkTo(backgroundImage.bottom)
                    start.linkTo(startGuideEditIcon)

                }
        )

    }

}

@Composable
fun Body(contactDetailViewModel: ContactDetailViewModel) {

    LazyColumn(modifier = Modifier) {

        items(
            contactDetailViewModel.contactDetailDataList.value!!,
            key = { it.infoTitle }) { contactDetail ->

            ContactDetail(contactDetail = contactDetail)

        }

    }

}

@Composable
fun ContactDetail(contactDetail: ContactDetailData) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(
                10.dp
            ), verticalAlignment = Alignment.CenterVertically
    ) {

        Column(modifier = Modifier.weight(0.2f)) {

            if (!contactDetail.showImage) {

                Image(
                    modifier = Modifier,
                    painter = painterResource(id = contactDetail.iconResource),
                    contentDescription = stringResource(
                        id = R.string.detailContactHeaderWallpaper
                    )
                )

            }

            Spacer(modifier = Modifier.size(20.dp))

        }

        Column(modifier = Modifier.weight(0.75f)) {

            Text(
                text = stringResource(id = contactDetail.infoTitle),
                fontSize = 15.sp,
                fontWeight = FontWeight.Light
            )

            Spacer(modifier = Modifier.size(10.dp))

            if (!contactDetail.showImage) {

                Text(
                    text = contactDetail.infoValue,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.size(30.dp))

                Divider(modifier = Modifier.height(1.dp), color = Color(0xFFBFBFBF))

            } else {

                Image(
                    painter = painterResource(id = contactDetail.image),
                    contentDescription = stringResource(
                        id = R.string.mapImage
                    )
                )

            }

        }

    }

    Spacer(modifier = Modifier.size(30.dp))

}



