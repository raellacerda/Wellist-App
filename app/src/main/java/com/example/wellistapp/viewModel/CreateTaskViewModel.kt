package com.example.wellistapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.wellistapp.data.Task
import com.example.wellistapp.data.TaskRepository
import com.example.wellistapp.uiState.CreateTaskUiState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CreateTaskViewModel @Inject constructor(private val repository: TaskRepository)
    : ViewModel() {

    private val _uiState = MutableStateFlow(CreateTaskUiState())
    val uiState: StateFlow<CreateTaskUiState> =  _uiState.asStateFlow()


    fun actionSaveButton (task: Task) {

    }



}