package com.example.compose.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.ui.component.TextButton

@Composable
fun Page1(navController: NavController){

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ) {
        TextButton("Back", onClick = { navController.popBackStack() })
    }

}