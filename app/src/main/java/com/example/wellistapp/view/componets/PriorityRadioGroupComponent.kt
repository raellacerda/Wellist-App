package com.example.wellistapp.view.componets


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.wellistapp.data.Priority
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.Text
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.wellistapp.R
import com.example.wellistapp.ui.theme.FocusedColor

@Composable
fun PriorityRadioGroupComponent (
    modifier: Modifier = Modifier,
    selectedOption : String,
    onOptionSelected: (String) -> Unit
) {


    val radioOptions = listOf(
        Priority.HIGH.toString(),
        Priority.MEDIUM.toString(),
        Priority.LOW.toString()
    )

    Column(
        modifier = modifier.selectableGroup()
            .fillMaxWidth()
    ) {
        radioOptions.forEach {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .selectable(
                        selected = (it == selectedOption),
                        onClick = {onOptionSelected(it)},
                        role = Role.RadioButton
                    )
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                RadioButton(
                    selected = (it == selectedOption),
                    onClick = null,
                    colors = RadioButtonColors(
                        selectedColor = FocusedColor,
                        unselectedColor = colorResource(R.color.gray),
                        disabledSelectedColor = colorResource(R.color.gray),
                        disabledUnselectedColor = colorResource(R.color.gray)
                    )
                )

                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
