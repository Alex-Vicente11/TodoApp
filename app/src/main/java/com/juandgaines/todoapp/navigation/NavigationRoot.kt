package com.juandgaines.todoapp.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import com.juandgaines.todoapp.presentation.screens.detail.TaskScreenRoot
import com.juandgaines.todoapp.presentation.screens.home.HomeScreenRoot
import kotlinx.serialization.Serializable
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

@Composable
fun NavigationRoot(
    navController: NavHostController,
) {
    Box(
        modifier = Modifier.fillMaxSize(),

        ) {
        NavHost(
            navController = navController,
            startDestination = HomeScreenDes
        ) {
            composable<HomeScreenDes> {
                HomeScreenRoot(
                    navigateToTaskScreen = {
                        navController.navigate(TaskScreenDes)
                    }
                )
            }

            composable<TaskScreenDes> {
                TaskScreenRoot(
                    navigateBack = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@Serializable
object HomeScreenDes

@Serializable
object TaskScreenDes