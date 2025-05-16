package com.example.wellistapp.view


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wellistapp.R
import com.example.wellistapp.data.Priority
import com.example.wellistapp.view.componets.DatePickerComponent
import com.example.wellistapp.view.componets.PriorityRadioGroupComponent
import com.example.wellistapp.view.componets.TextBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Create task")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "Navigation Icon"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Column (
            modifier = Modifier
                .padding(it),
            horizontalAlignment = Alignment.Start
        ) {
            var stateOfTaskName by remember { mutableStateOf("") }
            var stateOfDescriptionName by remember { mutableStateOf("")}
            val focusManager = LocalFocusManager.current
            val focusRequesterDescription = remember { FocusRequester()}
            var selectedPriority by remember { mutableStateOf(Priority.LOW.toString()) }
            var dateSelected by remember { mutableStateOf<String>("Choose a deadline") }

            TextBox(
                value = stateOfTaskName,
                onValueChange = {stateOfTaskName = it},
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp),
                label = "Name",
                maxLines = 1,
                keyboardType = KeyboardType.Text,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusRequesterDescription.requestFocus()
                    }
                ),
                imeAction = ImeAction.Next
            )
            TextBox(
                value = stateOfDescriptionName,
                onValueChange = {stateOfDescriptionName = it},
                modifier = Modifier.fillMaxWidth()
                    .padding(20.dp)
                    .focusRequester(focusRequesterDescription),
                label = "Description",
                maxLines = 4,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.clearFocus()
                    }
                )
            )
            DatePickerComponent(dateSelected = dateSelected)

            Text(
                "Choose a priority",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp)
            )
            PriorityRadioGroupComponent(
                modifier = Modifier.padding(20.dp),
                selectedOption = selectedPriority,
                onOptionSelected = {selectedPriority = it}
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    // implementar transição dos dados
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Row (
                    modifier = Modifier.padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        painter = painterResource(R.drawable.baseline_add_task_24),
                        contentDescription = "icon add task",
                        modifier = Modifier.padding(end = 16.dp)
                    )
                    Text("Save")
                }

            }
        }
    }
}



