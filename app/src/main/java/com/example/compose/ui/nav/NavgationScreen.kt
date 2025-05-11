package com.example.compose.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.ui.home.HomeScreen
import com.example.compose.ui.pages.Page1

@Composable
fun NavigationScreen(){

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home"){
        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("page1") {
            Page1(navController = navController)
        }
    }

}