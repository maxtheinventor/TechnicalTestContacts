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
fun FeatureNotReadyYetInfo(customMessage: String = "", closeDialog: () -> Unit) {

    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                stringResource(id = R.string.information_U), color = colorResource(
                    id = R.color.yellow_warning
                ), fontWeight = FontWeight.Bold
            )
        },
        text = { Text("Esta parte de la APP no esta lista todavida.\n\n ¡Espere atento a estas increibles mejoras!\n\n$customMessage") },
        confirmButton = {
            TextButton(onClick = { closeDialog() }) {
                Text(stringResource(id = R.string.continue_U))
            }
        }
    )

}


