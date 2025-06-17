package com.chch.mycompose.ui.screen.checklist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ModeEdit
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

enum class EditMode {
    VIEW,
    EDIT,
    DELETE,
    ADD
}

@Composable
fun CheckableRow(
    item: CheckItem,
    mode: EditMode = EditMode.VIEW,
    onCheckedChange: (Boolean) -> Unit,
    onDelete: (CheckItem) -> Unit,
    onEdit: (CheckItem) -> Unit
) {
    val textColor = MaterialTheme.colorScheme.primary
    val disableTextColor = MaterialTheme.colorScheme.onSurface.copy(0.38f)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .padding(horizontal = 12.dp, vertical = 4.dp)
            .clickable { onCheckedChange(!item.checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = item.checked,
            onCheckedChange = null // Row에서 제어할 때 null로 둠!
        )
        Spacer(Modifier.width(4.dp))
        Text(
            text = item.content,
            color = if (item.checked) disableTextColor else textColor,
            textDecoration = if (item.checked) TextDecoration.LineThrough else null,
            modifier = Modifier.padding(start = 8.dp)
        )
        Spacer(Modifier.weight(1f))
        when (mode) {
            EditMode.EDIT -> {
                IconButton(
                    onClick = { onEdit.invoke(item) }
                ) {
                    Icon(
                        Icons.Default.ModeEdit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            EditMode.DELETE -> {
                IconButton(
                    onClick = { onDelete.invoke(item) }
                ) {
                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            else -> {}
        }
    }
}