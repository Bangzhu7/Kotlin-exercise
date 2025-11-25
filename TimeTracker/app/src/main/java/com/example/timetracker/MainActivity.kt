package com.example.timetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.timetracker.data.AppDatabase
import com.example.timetracker.ui.screens.AddTaskScreen
import com.example.timetracker.ui.screens.StatsScreen
import com.example.timetracker.ui.screens.TaskListScreen
import com.example.timetracker.ui.theme.TimeTrackerTheme
import com.example.timetracker.viewmodel.TaskViewModel
import com.example.timetracker.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)
        val taskDao = database.taskDao()

        setContent {
            TimeTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TimeTrackerApp(
                        viewModelFactory = TaskViewModelFactory(taskDao)
                    )
                }
            }
        }
    }
}

@Composable
fun TimeTrackerApp(viewModelFactory: TaskViewModelFactory) {
    val navController = rememberNavController()
    val viewModel: TaskViewModel = viewModel(factory = viewModelFactory)

    NavHost(
        navController = navController,
        startDestination = "task_list"
    ) {
        composable("task_list") {
            TaskListScreen(
                viewModel = viewModel,
                onNavigateToAdd = { navController.navigate("add_task") },
                onNavigateToStats = { navController.navigate("stats") }
            )
        }

        composable("add_task") {
            AddTaskScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable("stats") {
            StatsScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}