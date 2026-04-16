package com.juandgaines.todoapp.presentation.screens.detail.providers

import androidx.compose.foundation.text.input.TextFieldState
import com.juandgaines.todoapp.domain.Category

data class TaskScreenState(
    val taskName: TextFieldState = TextFieldState(),
    val taskDescription: TextFieldState = TextFieldState(),
    val category:Category?  = null,
    val isTaskDone : Boolean = false,
)