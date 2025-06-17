package com.chch.mycompose.ui.screen.checklist

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

/*
Scaffold 안에 Scaffold는 권장하지 않는 방식
샘플 코드를 만들기 위해서 중첩 Scaffold를 사용했으며, WindowInsets(0)을 설정함.
* */
@Composable
fun ChecklistScreen(
    nc: NavController
) {
    var editMode by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf("") }
    var checklist = remember { mutableStateListOf<CheckItem>() }

    // Sample list
    checklist.add(CheckItem("산책하기"))
    checklist.add(CheckItem("청소기 돌리기"))

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            BottomAppBar(
                Modifier.height(80.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                windowInsets = WindowInsets(0),
            ) {
                Spacer(Modifier.weight(1f))
                FloatingActionButton(
                    onClick = { editMode = true },
                    modifier = Modifier.size(56.dp),
                    shape = CircleShape
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            }
        }

    ) { innerPadding ->
        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            LazyColumn(Modifier.fillMaxWidth()) {
                itemsIndexed(checklist) { index, item ->
                    CheckableRow(
                        item = item,
                    ) {
                        checklist[index] = item.copy(checked = it)
                    }
                    Divider()
                }
            }

            // Add a CheckItem
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = editMode,
                enter = slideInVertically(initialOffsetY = { +it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { +it }) + fadeOut()
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .background(color = MaterialTheme.colorScheme.surfaceContainer),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = editText,
                        onValueChange = { editText = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp, vertical = 4.dp)
                    )
                    IconButton(
                        onClick = {
                            editMode = false
                            if (editText.isNotBlank()) {
                                checklist.add(0, CheckItem(editText))
                                editText = ""
                            }
                        },
                        enabled = editText.isNotBlank(),
                        modifier = Modifier
                            .padding(end = 10.dp)
                            .background(
                                color = if (editText.isNotBlank()) MaterialTheme.colorScheme.primary
                                else MaterialTheme.colorScheme.primary.copy(0.38f),
                                shape = CircleShape
                            )
                    ) {
                        Icon(
                            Icons.Default.Done,
                            contentDescription = "Done",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }

            }

        }

    }
}
