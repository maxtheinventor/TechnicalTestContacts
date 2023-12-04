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
fun DownloadHasFailedWarning(continueButton: () -> Unit) {

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                stringResource(id = R.string.downloadFailed_U), color = colorResource(
                    id = R.color.red_error
                ), fontWeight = FontWeight.Bold
            )
        },
        text = { Text("La descarga de los contactos ha fallado.\n\nPor favor intentelo de nuevo mas tarde y pongase en contacto con el servicio técnico si el problema persiste") },
        confirmButton = {
            TextButton(onClick = { continueButton() }) {
                Text(stringResource(id = R.string.continue_U))
            }
        },
        dismissButton = {}
    )

}