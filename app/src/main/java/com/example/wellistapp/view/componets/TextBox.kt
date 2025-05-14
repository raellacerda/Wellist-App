package com.example.wellistapp.view.componets

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.wellistapp.ui.theme.FocusedColor
import com.example.wellistapp.ui.theme.ShapeEditText


@Composable
fun TextBox(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label : String,
    maxLines : Int,
    keyboardType: KeyboardType,
    keyboardActions: KeyboardActions,
    imeAction: ImeAction
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, //defini o tipo do teclado
            imeAction = imeAction //adiciona um icone no teclado para uma ação especifica
        ),
        shape = ShapeEditText.small,
        keyboardActions = keyboardActions, // adiciona um comportamento para a utilização do imeAction
        colors = TextFieldDefaults.colors( //estou adicionando cor quando o componente e selecionado
            focusedTextColor = FocusedColor, // to usando na borda do text, label e cursor
            focusedLabelColor = FocusedColor,
            cursorColor = FocusedColor
        )
    )
}