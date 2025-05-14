package com.example.wellistapp.view


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wellistapp.R
import com.example.wellistapp.view.componets.TextFieldComponent


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Create task")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        androidx.compose.material3.Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                            contentDescription = "Navigation Icon"
                        )
                    }
                },
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        Column (
            modifier = Modifier.padding(it)
                .fillMaxSize()
        ) {
            Spacer(Modifier.height(16.dp))
            TextFieldComponent("Title")

        }


    }
}



