package com.chch.mycompose.ui.screen.buttons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ButtonsPage(navController: NavController){

    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(10.dp)
    ) {

        Button(onClick = {}) {
            Text("Button")
        }

        Spacer(Modifier.padding(vertical = 10.dp))

        CustomButton("Custom Button", onClick = { /* navController.popBackStack() */})

        Spacer(Modifier.padding(vertical = 10.dp))

        FilledTonalButton(onClick = {}) {
            Text("Filled Tonal Button")
        }

        Spacer(Modifier.padding(vertical = 10.dp))

        OutlinedButton(onClick = {}) {
            Text("Outlined Button")
        }

        Spacer(Modifier.padding(vertical = 10.dp))

        ElevatedButton(onClick = {}) {
            Text("Elevated Button")
        }

        Spacer(Modifier.padding(vertical = 10.dp))

        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = "Android Icon"
            )
        }

        Spacer(Modifier.padding(vertical = 10.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.weight(1f).padding(12.dp)) {
                    Text("Card with IconButton")
                    if(expanded){
                        Text("Extended Status", style = MaterialTheme.typography.labelLarge)
                    }
                }

                IconButton(onClick = { expanded = !expanded }) {
                    Icon(
                        imageVector = if(expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                        contentDescription = "expand Icon"
                    )
                }

            }
        }

        Spacer(Modifier.padding(vertical = 10.dp))

        ButtonWithIcon(
            text = "Button with Icon",
            icon = Icons.Default.Diamond,
            iconDescription = "diamond",
            onClick = { }
        )

        Spacer(Modifier.padding(vertical = 10.dp))
    }

}

@Composable
fun CustomButton(
    text: String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(onClick, modifier,
        shape= RectangleShape,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 10.dp
        )
    ) {
        Text(text)
    }
}

@Composable
fun ButtonWithIcon(
    text: String,
    icon : ImageVector,
    iconDescription : String,
    onClick: ()->Unit,
    modifier: Modifier = Modifier
){
    Button(onClick, modifier, shape = RoundedCornerShape(5.dp)) {
        Icon(imageVector = icon, contentDescription = iconDescription, modifier = Modifier.padding(end = 10.dp))
        Text(text)
    }
}