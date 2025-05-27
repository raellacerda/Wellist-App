package com.example.wellistapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.wellistapp.viewModel.TasksViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wellistapp.view.componets.TaskItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navController: NavController
) {
    val viewModel: TasksViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.getTasksData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                title = {
                    // Pode ou não ter algo escrito
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("CreateTaskScreen")
                }
            ) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { innerPadding ->
        // esse componente sera alterado para LazyColumn quando tivermos os itens
        Column (
            modifier = Modifier.padding(innerPadding)
                .fillMaxSize()
        ) {
               when {
                   uiState.isLoading -> {
                       Box(
                           modifier = Modifier.fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                           CircularProgressIndicator()
                       }
                   }

                   uiState.errorMessage != null -> {
                       Box(
                           modifier = Modifier.fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                           Text(
                               text = uiState.errorMessage ?: "Unknown",
                               color = Color.Red,
                               textAlign = TextAlign.Center
                           )
                       }
                   }

                   uiState.tasks.isEmpty() -> {
                       Box(
                           modifier = Modifier.fillMaxSize(),
                           contentAlignment = Alignment.Center
                       ) {
                           Text("No tasks registered yet")
                       }
                   }
                   else -> {
                       LazyColumn(
                           modifier = Modifier
                               .fillMaxSize()
                               .padding(horizontal = 8.dp, vertical = 8.dp),
                           verticalArrangement = Arrangement.spacedBy(8.dp)
                       ) {
                           items(uiState.tasks) { task ->
                               TaskItem(
                                   task = task,
                                   onEdit = {},
                                   onDelete = { taskId -> viewModel.deleteTask(task)},
                                   onCheckedChange = {isChecked -> viewModel.updateTaskChecked(
                                       task,
                                       isChecked
                                   )}
                               )
                           }

                       }
                   }
               }
        }
    }
}


