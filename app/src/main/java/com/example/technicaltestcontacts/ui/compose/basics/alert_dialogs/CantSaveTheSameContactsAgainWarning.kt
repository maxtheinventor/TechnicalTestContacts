package com.example.technicaltestcontacts.ui.compose.basics.alert_dialogs

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.technicaltestcontacts.R

@Composable
fun CantSaveTheSameContactsAgainWarning(continueButton: () -> Unit){

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                stringResource(id = R.string.savingError_U), color = colorResource(
                    id = R.color.yellow_warning
                ), fontWeight = FontWeight.Bold
            )
        },
        text = { Text("No se pueden guardar en la APP los mismos contactos más de una vez") },
        confirmButton = {
            TextButton(onClick = { continueButton() }) {
                Text(stringResource(id = R.string.continue_U))
            }
        },
        dismissButton = {}
    )

}