package com.example.wellistapp.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.wellistapp.data.Task
import com.example.wellistapp.view.componets.TaskItem
import androidx.compose.runtime.setValue
import androidx.compose.runtime.remember


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TasksScreen(
    navController: NavController
) {
    val viewModel: TasksViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    var showDeleteDialog by remember { mutableStateOf(false) }
    var taskToDelete by remember { mutableStateOf<Task?>(null) }

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
                title = {  }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("CreateTaskScreen") }
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            when {
                uiState.isLoading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                uiState.errorMessage != null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(
                            text = uiState.errorMessage ?: "Unknown",
                            color = Color.Red,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                uiState.tasks.isEmpty() -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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

                            var checkedState by remember(task.id) { mutableStateOf(task.isDone) }

                            TaskItem(
                                task = task.copy(isDone = checkedState),
                                onEdit = {
                                    navController.navigate("CreateTaskScreen?taskId=${task.id}")
                                },
                                onDelete = {
                                    viewModel.deleteTask(task)
                                },
                                onCheckedChange = { isChecked ->
                                    checkedState = isChecked
                                    if (isChecked) {
                                        taskToDelete = task
                                        showDeleteDialog = true
                                    }
                                }
                            )

                        }
                    }
                }
            }

            if (showDeleteDialog && taskToDelete != null) {
                AlertDialog(
                    onDismissRequest = {
                        showDeleteDialog = false
                        taskToDelete = null
                    },
                    title = { Text("Confirmar exclusão") },
                    text = { Text("Você marcou a tarefa como concluída. Deseja deletá-la?") },
                    confirmButton = {
                        TextButton(onClick = {
                            taskToDelete?.let { viewModel.deleteTask(it) }
                            showDeleteDialog = false
                            taskToDelete = null
                        }) {
                            Text("Deletar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = {
                            showDeleteDialog = false
                            taskToDelete = null
                        }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}






