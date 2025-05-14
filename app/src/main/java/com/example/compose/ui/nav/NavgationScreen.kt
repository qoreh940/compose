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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.R
import com.example.compose.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(){

    val navController = rememberNavController()
    var showBackKey by remember { mutableStateOf(false) }

    val naviRoutes = getRoutes()
    val homeScreen = NaviScreenRoute("home", stringResource(R.string.home)){ _, nc -> HomeScreen(nc, naviRoutes) }
    var topTitle by remember { mutableStateOf(homeScreen.title) }


    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBackKey = navController.previousBackStackEntry != null
            topTitle =
                if(destination.route == homeScreen.route){
                    homeScreen.title
                } else {
                    naviRoutes.firstOrNull { it.route == destination.route }?.title
                        ?: "Invalid Page"
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
                    HomeScreen(navController, naviRoutes)
                }

                naviRoutes.forEach { screen ->
                    composable(screen.route) { backStackEntry ->
                        screen.content(backStackEntry, navController)
                    }
                }
            }
        }
    )

}

