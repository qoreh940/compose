package com.chch.mycompose.ui.screen.selection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chch.mycompose.ui.component.BorderedSection
import com.chch.mycompose.ui.component.CheckboxWithText
import com.chch.mycompose.ui.component.RadioWithText

@Composable
fun RadioCheckboxPage(
    nc: NavController,
) {
    val scrollState = rememberScrollState()

    var selectedGender by remember { mutableStateOf(Gender.FEMALE) }

    val toppingList = getToppingList()
    var selectedToppings by remember { mutableStateOf(setOf<Topping>()) }

    val agreementOptions = getAgreementOptions()
    var selectedAgreementOptions by remember { mutableStateOf(setOf<AgreementOption>()) }

    val radioState by remember(selectedAgreementOptions) {
        derivedStateOf {
            when (selectedAgreementOptions.size) {
                0 -> AgreementState.NONE
                agreementOptions.size -> AgreementState.ALL
                else -> AgreementState.INDETERMINATE
            }
        }
    }

    Column(Modifier
        .fillMaxSize()
        .verticalScroll(scrollState)) {

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

        Spacer(Modifier.height(20.dp))

        // Checkbox Sample Example
        BorderedSection(
            title = "Extra Toppings"
        ) {
            Column {
                toppingList.forEach { topping ->
                    CheckboxWithText(
                        text = "${topping.name} (+${topping.price})",
                        checked = selectedToppings.contains(topping)
                    ) { checked ->
                        selectedToppings = if (checked) {
                            selectedToppings + topping
                        } else {
                            selectedToppings - topping
                        }
                    }
                }

                Text(
                    text = "Additional amount: ${selectedToppings.sumOf { it.price }}",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(top = 10.dp, start = 12.dp)
                )
            }
        }

        Spacer(Modifier.height(20.dp))

        // Radio Button with Checkbox
        BorderedSection(
            title = "Agreement"
        ) {

            Column {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .selectableGroup()
                ) {

                    AgreementState.entries.forEach { state ->
                        RadioWithText(
                            modifier = Modifier.weight(1f),
                            selected = state == radioState,
                            text = state.displayName,
                        ) {
                            when (state) {
                                AgreementState.ALL -> selectedAgreementOptions =
                                    agreementOptions.toSet()

                                AgreementState.NONE -> selectedAgreementOptions = emptySet()
                                AgreementState.INDETERMINATE -> {
                                    // Do Nothing
                                }
                            }
                        }
                    }
                }

                Column {
                    agreementOptions.forEach { opt ->
                        CheckboxWithText(
                            text = opt.name,
                            checked = selectedAgreementOptions.contains(opt)
                        ) { checked ->
                            selectedAgreementOptions = if (checked) {
                                selectedAgreementOptions + opt
                            } else {
                                selectedAgreementOptions - opt
                            }
                        }
                    }
                }

            }


        }
    }


}