package com.example.wellistapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellistapp.data.Task
import com.example.wellistapp.data.TaskRepository
import com.example.wellistapp.uiState.TasksListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TasksViewModel @Inject constructor(private val repository: TaskRepository) : ViewModel(){

    private val _uiState = MutableStateFlow(TasksListUiState())
    val uiState: StateFlow<TasksListUiState> =  _uiState.asStateFlow()

    private val _taskBeingEdited = MutableStateFlow<Int?>(null)
    val taskBeingEdited : StateFlow<Int?> = _taskBeingEdited

    init {
        getTasksData()
    }

    internal fun getTasksData() {
       viewModelScope.launch{

           _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

           try {
               repository.getAllTasks().collect {
                   _uiState.value = _uiState.value.copy(tasks = it, isLoading = false)
               }

           } catch (e: Exception) {
               _uiState.value = _uiState.value.copy(
                   isLoading = false,
                   errorMessage = "error loading tasks ${e.message}"
               )
           }
       }
    }
    fun deleteTask (task: Task) {
        viewModelScope.launch {
            try {
                repository.delete(task)
                getTasksData()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    errorMessage = "Error deleting task: ${e.message}"
                )
            }
        }
    }

    fun updateTaskChecked(task: Task, isDone: Boolean) {
        viewModelScope.launch {
            try {
                val task = _uiState.value.tasks.find { it.id == task.id }
                task?.let {
                    val updatedTask = it.copy(isDone = isDone)
                    repository.update(updatedTask)
                    getTasksData()
                }
            } catch(e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = "Error updating task: ${e.message} ")
            }
        }
    }

    fun onEditTask(task: Task) {
        _taskBeingEdited.value = task.id
    }



}