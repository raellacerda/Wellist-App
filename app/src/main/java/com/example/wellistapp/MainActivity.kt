package com.example.wellistapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wellistapp.ui.theme.WellistAppTheme
import com.example.wellistapp.view.CreateTaskScreen
import com.example.wellistapp.view.TasksScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WellistAppTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "TasksScreen") {
                    composable(route = "TasksScreen") {
                        TasksScreen(navController)
                    }
                    composable(route = "CreateTaskScreen") {
                        CreateTaskScreen(navController)
                    }
                }
            }
        }
    }
}

