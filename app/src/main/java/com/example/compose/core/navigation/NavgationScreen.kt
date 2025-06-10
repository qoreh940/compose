package com.example.compose.core.navigation

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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.example.compose.R
import com.example.compose.ui.screen.home.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen() {

    val navController = rememberNavController()
    var showBackKey by remember { mutableStateOf(false) }

    val naviRoutes = getRoutes()
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route
    val childRoutes = getRoutes()
    val currentTitle = childRoutes
        .firstOrNull { it.route == currentRoute }
        ?.let { stringResource(it.titleRes) }
        ?: stringResource(R.string.home)

    LaunchedEffect(navController) {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            showBackKey = navController.previousBackStackEntry != null

        }

    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = currentTitle, fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                navigationIcon = {
                    if (showBackKey) {
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
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier.padding(padding)
            ) {

                composable("home") {
                    HomeScreen(navController, naviRoutes)
                }

                childRoutes.forEach { screen ->
                    composable(
                        route = screen.route,
                        deepLinks = screen.deepLinkUri?.takeIf { it.isNotBlank() }?.let { uri ->
                            listOf(navDeepLink {
                                uriPattern = uri
                            })
                        } ?: emptyList()
                    ) { backStackEntry ->
                        screen.content(backStackEntry, navController)
                    }
                }
            }
        }
    )

}

