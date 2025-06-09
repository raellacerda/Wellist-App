package com.example.wellistapp.uiState

import com.example.wellistapp.data.Priority


data class CreateTaskUiState(
    val taskId: Int? = null, // <- novo campo
    val taskName: String? = null,
    val taskDescription: String? = null,
    val taskPriority: Priority? = null,
    val taskDate: Long? = null,
    val isSaving: Boolean = false,
    val errorMessage: String? = null
)
