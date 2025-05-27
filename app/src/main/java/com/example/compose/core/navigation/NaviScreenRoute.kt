package com.example.compose.ui.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.compose.R
import com.example.compose.ui.screen.buttons.ButtonsPage
import com.example.compose.ui.screen.dialogs.DialogsPage

//Defined routes in Navigation using the NaviScreenRoute data class
data class NaviScreenRoute(
    val route : String,
    val title : String,
    val content : @Composable (NavBackStackEntry, NavController) -> Unit
)

@Composable
fun getRoutes() : List<NaviScreenRoute> {

    val secondScreenList = mutableListOf(
        NaviScreenRoute("buttons", stringResource(R.string.buttons)){ _, nc ->
            ButtonsPage(nc)
        },
        NaviScreenRoute("dialogs", stringResource(R.string.dialogs)){ _, nc ->
            DialogsPage(nc)
        }
    )

    return secondScreenList
}