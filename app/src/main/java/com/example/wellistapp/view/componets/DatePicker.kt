package com.example.wellistapp.view.componets


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.wellistapp.R




@ExperimentalMaterial3Api
@Composable
fun DatePickerComponent (
    modifier: Modifier = Modifier,
    dateSelected : String
) {


    //precisa elevar o estado
    val openDialog = remember { mutableStateOf(false) }
    var inputFinished = remember { mutableStateOf(false) }

    Row (
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
    ){
        IconButton(
            onClick = {
                openDialog.value = true
            }
        ) {
            Icon(
                painter = painterResource(R.drawable.baseline_calendar_today_24),
                contentDescription = ""
            )
        }
        Text(dateSelected)

        if (openDialog.value) {

            val datePickerState = rememberDatePickerState()

            val confirmEnabled = remember {
                derivedStateOf { datePickerState.selectedDateMillis != null }
            }

            DatePickerDialog(
                onDismissRequest = {
                    openDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDialog.value = false
                            dateSelected = "${datePickerState.selectedDateMillis}"
                            inputFinished.value = true

                        },
                        enabled = confirmEnabled.value
                    ) {
                        Text("OK")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { openDialog.value = false }) { Text("Cancel") }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.verticalScroll(rememberScrollState())
                )
            }
        }

    }

}