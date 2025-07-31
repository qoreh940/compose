package com.chch.mycompose.ui.screen.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chch.mycompose.ui.component.BorderedSection
import com.chch.mycompose.ui.component.RadioWithText

@Composable
fun RadioCheckboxPage(
    nc: NavController,
) {
    var selectedGender by remember { mutableStateOf(Gender.FEMALE) }

    Column(Modifier.fillMaxSize()) {

        // Radio buttons for Gender
        BorderedSection(
            title = "Gender"
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .selectableGroup()
            ) {

                Gender.entries.forEach { gender ->
                    RadioWithText(
                        modifier = Modifier.weight(1f),
                        selected = gender == selectedGender,
                        text = gender.displayName,
                    ) {
                        selectedGender = gender
                    }
                }
            }
        }

        //
    }


}