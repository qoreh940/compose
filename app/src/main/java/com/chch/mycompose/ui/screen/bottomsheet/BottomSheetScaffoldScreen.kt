package com.chch.mycompose.ui.screen.bottomsheet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun BottomSheetScaffoldScreen(
    nc: NavController
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetShape = RoundedCornerShape(topEnd = 20.dp, topStart = 20.dp),
        sheetPeekHeight = 128.dp,
        sheetContent = {
            Column(
                Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
                    .heightIn(500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.height(20.dp))
                Text("Swipe Up & Down")
                Spacer(Modifier.height(20.dp))
                Text("Material3 does not support the expand() and collapse() functions as of the official documentation in June 2025. To use these functions, you need to use Material2. Therefore, I used Material2 in my project.")
                Button(
                    onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.collapse()
                        }
                    }
                ) {
                    Text("Hide BottomSheetScaffold")
                }
            }
        },

        ) { innerPadding ->
        Box(Modifier.fillMaxSize()) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(),
            ) {
                Button(
                    onClick = {
                        scope.launch {
                            scaffoldState.bottomSheetState.expand()
                        }
                    }
                ) {
                    Text("Show BottomSheetScaffold")
                }
            }
        }
    }
}