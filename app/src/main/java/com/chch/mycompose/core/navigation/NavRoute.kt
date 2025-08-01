package com.chch.mycompose.core.navigation

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.chch.mycompose.R
import com.chch.mycompose.ui.screen.bottomsheet.BottomSheetScaffoldScreen
import com.chch.mycompose.ui.screen.bottomsheet.BottomSheetScreen
import com.chch.mycompose.ui.screen.buttons.ButtonsPage
import com.chch.mycompose.ui.screen.checklist.ChecklistScreen
import com.chch.mycompose.ui.screen.dialogs.DialogsPage
import com.chch.mycompose.ui.screen.imageslider.ImageSliderScreen
import com.chch.mycompose.ui.screen.numeric.NumericKeypadPage
import com.chch.mycompose.ui.screen.pip.PIPScreen
import com.chch.mycompose.ui.screen.selection.RadioCheckboxPage

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
            deepLinkUri = "chch-compose://buttons/"
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
        },
        NavRoute(
            route = "numeric",
            titleRes = R.string.numeric_keypad,
            deepLinkUri = null
        ) { _, nc ->
            NumericKeypadPage(nc)
        },
        NavRoute(
            route = "imageSlider",
            titleRes = R.string.image_slider,
            deepLinkUri = null
        ) { _, nc ->
            ImageSliderScreen(nc)
        },
        NavRoute(
            route = "bottomSheet",
            titleRes = R.string.modalbottomsheet,
            deepLinkUri = null
        ) { _, nc ->
            BottomSheetScreen(nc)
        },
        NavRoute(
            route = "bottomSheetScaffold",
            titleRes = R.string.bottomsheetscaffold,
            deepLinkUri = null
        ) { _, nc ->
            BottomSheetScaffoldScreen(nc)
        },
        NavRoute(
            route = "checklist",
            titleRes = R.string.checklist,
            deepLinkUri = null
        ) { _, nc ->
            ChecklistScreen(nc)
        },
        NavRoute(
            route = "radioAndCheckbox",
            titleRes = R.string.radio_and_checkbox,
            deepLinkUri = null
        ) { _, nc ->
            RadioCheckboxPage(nc)
        }

    )

    return secondScreenList
}