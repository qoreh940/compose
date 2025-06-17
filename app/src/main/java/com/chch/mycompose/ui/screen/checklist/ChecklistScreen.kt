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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
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
    var showInput by remember { mutableStateOf(false) }
    var editText by remember { mutableStateOf("") }
    var editItem by remember { mutableStateOf<CheckItem?>(null) }
    var editMode by remember { mutableStateOf(EditMode.VIEW) }
    var checklist = remember {
        mutableStateListOf<CheckItem>().apply {
            add(CheckItem("산책하기"))
            add(CheckItem("청소기 돌리기"))
        }
    }

    fun resetInputState() {
        showInput = false
        editText = ""
        editItem = null
    }

    fun handleChecklistInput() {
        if (editText.isNotBlank()) {
            when (editMode) {
                EditMode.ADD -> {
                    checklist.add(0, CheckItem(editText))
                }

                EditMode.EDIT -> {
                    editItem?.let {
                        val index = checklist.indexOf(it)
                        checklist[index] = it.copy(content = editText)
                    }
                }

                else -> { /* Do Nothing */
                }
            }

        }
        resetInputState()
        editMode = EditMode.VIEW
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0),
        bottomBar = {
            BottomAppBar(
                Modifier.height(80.dp),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                windowInsets = WindowInsets(0),
            ) {
                AnimatedModeButton(
                    isActive = editMode == EditMode.EDIT,
                    icon = Icons.Default.ModeEdit,
                    contentDescription = "Edit Mode Button",
                    onClick = {
                        resetInputState()
                        editMode = if (editMode == EditMode.EDIT) EditMode.VIEW else EditMode.EDIT
                    }
                )

                AnimatedModeButton(
                    isActive = editMode == EditMode.DELETE,
                    icon = Icons.Default.Delete,
                    contentDescription = "Delete Mode Button",
                    onClick = {
                        resetInputState()
                        editMode =
                            if (editMode == EditMode.DELETE) EditMode.VIEW else EditMode.DELETE
                    }
                )

                Spacer(Modifier.weight(1f))
                FloatingActionButton(
                    onClick = {
                        if (showInput)
                            resetInputState()
                        else
                            showInput = true
                        editMode = if (editMode == EditMode.ADD) EditMode.VIEW else EditMode.ADD
                    },
                    modifier = Modifier.size(56.dp),
                    shape = CircleShape
                ) {
                    Icon(
                        if (editMode == EditMode.ADD) Icons.Default.Clear else Icons.Default.Add,
                        contentDescription = "Add an item"
                    )
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
                        mode = editMode,
                        onCheckedChange = {
                            checklist[index] = item.copy(checked = it)
                        },
                        onEdit = {
                            editText = it.content
                            editItem = it
                            showInput = true
                        },
                        onDelete = {
                            checklist.remove(it)
                        }
                    )

                    HorizontalDivider()
                }
            }

            // Add a CheckItem
            AnimatedVisibility(
                modifier = Modifier.align(Alignment.BottomCenter),
                visible = showInput,
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
                        placeholder = {
                            Text(
                                "Please enter a checklist item",
                                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.6f)
                            )
                        },
                        onValueChange = { editText = it },
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp, vertical = 4.dp),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { handleChecklistInput() }
                        )
                    )
                    IconButton(
                        onClick = { handleChecklistInput() },
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
