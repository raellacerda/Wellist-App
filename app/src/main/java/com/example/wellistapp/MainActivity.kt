package com.example.wellistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.wellistapp.ui.theme.WellistAppTheme
import com.example.wellistapp.view.CreateTaskScreen
import com.example.wellistapp.view.TasksScreen
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WellistAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "TasksScreen"
                ) {
                    composable("TasksScreen") {
                        TasksScreen(navController = navController)
                    }

                    composable(
                        route = "CreateTaskScreen?taskId={taskId}",
                        arguments = listOf(
                            navArgument("taskId") {
                                type = NavType.StringType
                                nullable = true
                                defaultValue = null
                            }
                        )
                    ) { backStackEntry ->
                        val taskIdString = backStackEntry.arguments?.getString("taskId")
                        val taskId = taskIdString?.toIntOrNull()
                        CreateTaskScreen(navController = navController, taskId = taskId)
                    }
                }
            }
        }
    }
}




