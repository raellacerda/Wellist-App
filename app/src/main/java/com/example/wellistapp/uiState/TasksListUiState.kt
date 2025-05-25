package com.example.wellistapp.uiState

import com.example.wellistapp.data.Task
import kotlinx.coroutines.flow.Flow

data class TasksListUiState (
    val tasks: List<Task> = emptyList() ,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)