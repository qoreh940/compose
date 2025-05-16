package com.example.compose.ui.pages

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.ui.component.BasicAlertDialog
import com.example.compose.ui.component.PopoverItem
import com.example.compose.ui.component.PopupExample
import com.example.compose.ui.component.RowPopover
import com.example.compose.ui.component.SimpleDialog

@Composable
fun DialogsPage(navController: NavController) {

    var showBasicAlertDialog by remember { mutableStateOf(false) }
    var showSimpleDialog by remember { mutableStateOf(false) }
    var showPopupExample by remember { mutableStateOf(false) }
    var showRowPopover by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Button(onClick = { showBasicAlertDialog = true }) {
            Text("Show BasicAlertDialog")
        }

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Button(onClick = { showSimpleDialog = true }) {
            (Text("Show SimpleDialog"))
        }

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Box {
            Button(onClick = { showPopupExample = true }) {
                (Text("Show PopupExample"))
            }

            if (showPopupExample) {
                PopupExample(
                    onDismissRequest = { showPopupExample = false },
                    context = "This is PopupExample"
                )
            }
        }

        Spacer(modifier = Modifier.padding(vertical = 10.dp))

        Box {
            Button(onClick = { showRowPopover = true }) {
                (Text("Show RowPopover"))
            }

            if (showRowPopover) {
                RowPopover(
                    items = listOf(
                        PopoverItem("Copy", { showRowPopover = false }),
                        PopoverItem("Paste", { showRowPopover = false })
                    )
                ) {
                    showRowPopover = false
                }
            }
        }

    }

    when {
        showBasicAlertDialog -> {
            BasicAlertDialog(
                onConfirmation = { showBasicAlertDialog = false },
                onDismissRequest = { showBasicAlertDialog = false },
                showDismissButton = true,
                title = "BasicAlertDialog",
                contentText = "This is AlertDialog.",
                icon = Icons.Default.Info
            )
        }

        showSimpleDialog -> {
            SimpleDialog(
                onDismissRequest = { showSimpleDialog = false },
                context = "This is SimpleDialog. This is SimpleDialog. This is SimpleDialog."
            )
        }
    }

}


