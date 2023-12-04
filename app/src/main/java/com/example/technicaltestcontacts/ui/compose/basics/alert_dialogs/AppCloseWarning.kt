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
fun AppCloseWarning (closeApp: (value: Boolean) -> Unit){

    AlertDialog(
        onDismissRequest = {},
        title = {Text(
                stringResource(id = R.string.exitApp_U), color = colorResource(
                    id = R.color.yellow_warning
                ), fontWeight = FontWeight.Bold
            )
        },
        text = { Text("Estás a punto de cerrar la APP.\n¿Estás seguro de esto?") },
        confirmButton = {
            TextButton(onClick = { closeApp(true) }) {
                Text(stringResource(id = R.string.yes))
            }
        },
        dismissButton = {
            TextButton(onClick = { closeApp(false) }) {
                Text(stringResource(id = R.string.cancel_U))
            }
        }
    )

}