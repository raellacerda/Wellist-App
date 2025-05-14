package com.example.wellistapp.view.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun TextFieldComponent(label: String) {

    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = {input = it},
        label = {
            Text(label)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)

    )
}



@Preview
@Composable
private fun TextFieldComponentPreview() {
    TextFieldComponent("Name")
}