package com.example.wellistapp.uiState

import com.example.wellistapp.data.Priority

data class CreateTaskUiState(
    val taskName: String? = null,
    val taskDescription: String? = null,
    val taskPriority: Priority? = null,
    val taskDate: Long? = null
)
