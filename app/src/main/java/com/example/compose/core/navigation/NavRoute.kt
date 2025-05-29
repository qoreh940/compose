package com.example.compose.core.navigation

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.ui.screen.buttons.ButtonsPage
import com.example.compose.ui.screen.dialogs.DialogsPage
import com.example.compose.ui.screen.pip.PIPScreen

//Defined routes in Navigation using the NaviScreenRoute data class
@SuppressLint("SupportAnnotationUsage")
data class NavRoute(
    val route: String,
    @StringRes val titleRes: Int,
    val deepLinkUri: String? = null,
    val content: @Composable (NavBackStackEntry, NavController) -> Unit
)

fun getRoutes(): List<NavRoute> {

    val secondScreenList = mutableListOf(
        NavRoute(
            route = "buttons",
            titleRes = R.string.buttons,
            deepLinkUri = "alice-compose://buttons/"
        ) { _, nc ->
            ButtonsPage(nc)
        },
        NavRoute(
            route = "dialogs",
            titleRes = R.string.dialogs_title,
            deepLinkUri = null
        ) { _, nc ->
            DialogsPage(nc)
        },
        NavRoute(
            route = "pip",
            titleRes = R.string.pip_title,
            deepLinkUri = null
        ) { _, nc ->
            PIPScreen(nc)
        }
    )

    return secondScreenList
}