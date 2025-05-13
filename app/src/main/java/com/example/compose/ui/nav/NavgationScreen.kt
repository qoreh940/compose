package com.example.compose.ui.nav

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.home.HomeScreen
import com.example.compose.ui.pages.Page1

const val HOME_TITLE = "Home"
const val PAGE1_TITLE = "Page1"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(){

    val navController = rememberNavController()
    var topTitle by remember { mutableStateOf(HOME_TITLE) }
    var showBackKey by remember { mutableStateOf(false) }

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBackKey = navController.previousBackStackEntry != null
            topTitle = when(destination.route){
                "home" -> HOME_TITLE
                "page1" -> PAGE1_TITLE
                else -> "Invalid Page"
            }
        }

    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {Text(text = topTitle, fontWeight = FontWeight.Bold)},
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    if(showBackKey) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBackIosNew,
                                contentDescription = "back"
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors()
                )
        },
        content = { padding ->
            NavHost(navController = navController, startDestination = "home", modifier = Modifier.padding(padding)){
                composable("home") {
                    HomeScreen(navController = navController)
                }

                composable("page1") {
                    Page1(navController = navController)
                }
            }
        }
    )



}