package com.chch.mycompose.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.chch.mycompose.R

@Composable
fun BasicAlertDialog(
    onConfirmation: () -> Unit,
    onDismissRequest: () -> Unit,
    showDismissButton : Boolean = false,
    icon : ImageVector? = null,
    title: String? = null,
    contentText: String,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = {
            TextButton(onClick = onConfirmation) {
                Text(stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            if(showDismissButton) {
                TextButton(onClick = onDismissRequest) {
                    Text(stringResource(R.string.dismiss))
                }
            }
        },
        icon = {
            icon?.let {
                Icon(imageVector = icon, contentDescription = "AlertDialog Icon")
            }
        },
        title = {
            title?.let {
                Text(title)
            }
        },
        text = {
            Text(contentText)
        }
    )
}

@Composable
fun SimpleDialog(
    onDismissRequest: () -> Unit,
    context : String,
    enableBackKey : Boolean = true
){
    Dialog(onDismissRequest = onDismissRequest) {
        Card{
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(text = context,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentSize(Alignment.Center)
                        .padding(start = 15.dp, end = 15.dp, top = 40.dp, bottom = 20.dp)
                )

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End) {
                    TextButton(onClick = onDismissRequest, modifier = Modifier.padding(end = 20.dp, bottom = 15.dp)) {
                        (Text(text = stringResource(R.string.dismiss)))
                    }
                }
            }

            BackHandler(enableBackKey) {
                // Do nothing
            }

        }

    }
}

