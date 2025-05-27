package com.example.compose.ui.screen.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.core.navigation.NavRoute

@Composable
fun HomeScreen(navController: NavController, screenRoutes: List<NavRoute>) {


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
//        TextButton("Move to page 1", onClick = { navController.navigate("buttons") })
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(screenRoutes) { screen ->
                CardItem(stringResource(screen.titleRes)) { navController.navigate(screen.route) }
                Spacer(Modifier.padding(vertical = 10.dp))
            }
        }
    }
}


@Composable
fun CardItem(text: String, clickListener: () -> Unit) {
    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp),
        onClick = clickListener,
        border = BorderStroke(2.dp, CardDefaults.cardColors().containerColor)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 15.dp, vertical = 15.dp),
            style = MaterialTheme.typography.titleMedium
        )
    }
}