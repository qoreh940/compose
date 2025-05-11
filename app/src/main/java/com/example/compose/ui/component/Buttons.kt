package com.example.compose.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape

@Composable
fun TextButton(
    text: String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier,
    shape : Shape = ButtonDefaults.shape
){
    Button(onClick, modifier, shape=shape) {
        Text(text)
    }
}