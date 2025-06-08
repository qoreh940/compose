package com.example.compose.ui.screen.numeric.numerickeypad

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.compose.R
import java.text.DecimalFormatSymbols

@Composable
fun KeypadKeys(
    typeDigit: (String) -> Unit,
    backspace: () -> Unit,
    done: () -> Unit = {},
){

    Row(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.weight(1f)) {
            DigitButton("7", Modifier.weight(1f), typeDigit = typeDigit)
            DigitButton("4", typeDigit = typeDigit)
            DigitButton("1", typeDigit = typeDigit)
            IconButton(iconResId = R.drawable.ic_plus_minus, "+/-", onClick = {
                typeDigit.invoke("-")
            })
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(Modifier.weight(1f)) {
            DigitButton("8", typeDigit = typeDigit)
            DigitButton("5", typeDigit = typeDigit)
            DigitButton("2", typeDigit = typeDigit)
            DigitButton("0", typeDigit = typeDigit)
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(Modifier.weight(1f)) {
            DigitButton("9", typeDigit = typeDigit)
            DigitButton("6", typeDigit = typeDigit)
            DigitButton("3", typeDigit = typeDigit)
            DigitButton(DecimalFormatSymbols.getInstance().decimalSeparator.toString(), typeDigit = typeDigit)
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column(Modifier.weight(1f)) {
            IconButton(imageVector = Icons.AutoMirrored.Filled.Backspace, "backspace", onClick = { backspace() })
            IconButton(imageVector = Icons.Default.Done, "Done", onClick = { done() }, Modifier.weight(3f))
        }
    }
}