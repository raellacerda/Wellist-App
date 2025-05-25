package com.example.wellistapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellistapp.data.Task
import com.example.wellistapp.data.TaskRepository
import com.example.wellistapp.uiState.CreateTaskUiState
import com.example.wellistapp.uiState.TasksListUiState
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TasksViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel(){
    private val _uiState = MutableStateFlow(TasksListUiState())
    val uiState: StateFlow<TasksListUiState> =  _uiState.asStateFlow()

    fun getTasksData() {
       viewModelScope.launch{
           repository.getAllTasks().collect {
               _uiState.value = _uiState.value.copy(tasks = it)
           }

       }
    }

}