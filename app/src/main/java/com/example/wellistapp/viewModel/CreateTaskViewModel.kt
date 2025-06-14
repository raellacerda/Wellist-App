package com.example.wellistapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wellistapp.data.Priority
import com.example.wellistapp.data.Task
import com.example.wellistapp.data.TaskRepository
import com.example.wellistapp.uiState.CreateTaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Date


@HiltViewModel
class CreateTaskViewModel @Inject constructor(private val repository: TaskRepository)
    : ViewModel() {

    private val _uiState = MutableStateFlow(CreateTaskUiState())
    val uiState: StateFlow<CreateTaskUiState> =  _uiState.asStateFlow()

    fun onChangeTaskName(name: String){
        _uiState.value = _uiState.value.copy(taskName = name, errorMessage = null)
    }

    fun onChangeTaskDescription (description: String) {
        _uiState.value = _uiState.value.copy(taskDescription = description, errorMessage = null)
    }

    fun onChangeTaskPriority (priority: Priority) {
        _uiState.value = _uiState.value.copy(taskPriority = priority, errorMessage = null)
    }

    fun onChangeTaskDate (date: Long) {
        _uiState.value = _uiState.value.copy(taskDate = date, errorMessage = null)
    }

    fun saveTask() {
        viewModelScope.launch {
            val current = _uiState.value

            if (current.taskName.isNullOrBlank()) {
                _uiState.value = current.copy(errorMessage = "name cannot be null or empty")
                return@launch
            }

            _uiState.value = current.copy(isSaving = true, errorMessage = null)

            try {
                val task = Task(
                    id = current.taskId ?: 0, // se 0, DAO deve tratar como nova inserção se necessário
                    title = current.taskName,
                    description = current.taskDescription ?: "",
                    priority = current.taskPriority ?: Priority.LOW,
                    term = current.taskDate ?: Date().time
                )

                if (current.taskId == null) {
                    repository.insert(task)
                } else {
                    repository.update(task)
                }

                _uiState.value = CreateTaskUiState() // limpa após salvar

            } catch (e: Exception) {
                _uiState.value = current.copy(
                    isSaving = false,
                    errorMessage = "Task save error ${e.message}"
                )
            }
        }
    }



    fun loadTask(taskId: Int) {
        viewModelScope.launch {
            repository.getTask(taskId).collect { task ->
                _uiState.value = CreateTaskUiState(
                    taskId = task.id,
                    taskName = task.title,
                    taskDescription = task.description,
                    taskPriority = task.priority,
                    taskDate = task.term
                )
            }
        }
    }


}