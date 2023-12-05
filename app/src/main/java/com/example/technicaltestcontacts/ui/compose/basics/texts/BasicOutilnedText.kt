package com.example.technicaltestcontacts.ui.compose.basics.texts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.technicaltestcontacts.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicOutlinedText(
    text: String,
    onTextChanged: (String) -> Unit,
    labelText: Int,
    keyboardType: KeyboardType,
    mainColor: Color,
    singleLine: Boolean,
    imeAction: ImeAction,
    isError: Boolean,
    errorMessage: Int,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = text,
        onValueChange = {
            onTextChanged(it)
        },
        singleLine = singleLine,
        label = {
            Text(text = stringResource(id = labelText))
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        shape = RoundedCornerShape(25.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(top = 10.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = mainColor,
            cursorColor = Color.Black,
            textColor = Color.Black,
            focusedBorderColor = mainColor,
            unfocusedLabelColor = Color.Black,
            focusedLabelColor = Color.Black,
            errorBorderColor = Color.Red,
            errorLabelColor = Color.Black
        ), isError = isError, trailingIcon = {
            if (isError) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.error_red),
                    contentDescription = stringResource(id = R.string.error),
                    modifier = Modifier.size(18.dp)
                )
            }

        }, supportingText = {
            if (isError) {

                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = stringResource(id = errorMessage),
                    color = Color.Red
                )
            }
        }
    )

}