package com.example.compose.ui.screen.numeric.numerickeypad

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NumericKeypad(
    modifier: Modifier = Modifier,
    typeDigit: (String) -> Unit,
    backspace: () -> Unit,
    done: () -> Unit,
){

    Box(modifier = modifier){
        KeypadKeys(
            typeDigit = typeDigit,
            backspace = backspace,
            done = done
        )
    }

}