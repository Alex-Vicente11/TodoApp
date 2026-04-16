package com.juandgaines.todoapp.presentation.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juandgaines.todoapp.data.FakeTaskLocalDataSource
import com.juandgaines.todoapp.domain.Task
import com.juandgaines.todoapp.presentation.screens.detail.ActionTask.*
import com.juandgaines.todoapp.presentation.screens.detail.providers.TaskScreenState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.UUID

class TaskViewModel: ViewModel() {
    private val fakeTaskLocalDataSource = FakeTaskLocalDataSource

    var state by mutableStateOf(TaskScreenState())
        private set

    private val eventsChannel = Channel<TaskEvent>()
    val events = eventsChannel.receiveAsFlow()

    fun onAction(actionTask: ActionTask){
        viewModelScope.launch {
            when(actionTask) {
                is ChangeTaskCategory -> {
                    state = state.copy(
                        category = actionTask.category
                    )
                }
                is ChangeTaskDone -> {
                    state = state.copy(
                        isTaskDone = actionTask.isTaskDone
                    )
                }
                SaveTask -> {
                    val task = Task(
                        id = UUID.randomUUID().toString(),
                        title = state.taskName.text.toString(),
                        description = state.taskDescription.text.toString(),
                        isCompleted = state.isTaskDone,
                        category = state.category
                    )

                    fakeTaskLocalDataSource.addTask(task)
                    eventsChannel.send(TaskEvent.TaskCreated)
                }

                else -> Unit
            }

        }
    }


}