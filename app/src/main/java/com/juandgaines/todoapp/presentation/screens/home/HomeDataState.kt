package com.juandgaines.todoapp.presentation.screens.home

import com.juandgaines.todoapp.domain.Task

class HomeDataState(
    val date: String,
    val summary: String,
    val completedTask: List<Task> = emptyList(),
    val pendingTask: List<Task> = emptyList()
)