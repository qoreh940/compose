package com.example.compose.ui.screen.numeric

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.compose.ui.screen.numeric.numerickeypad.NumericKeypad
import kotlinx.coroutines.launch
import java.text.DecimalFormatSymbols

@Composable
fun NumericKeypadPage(
    nc : NavController
){
    var value by remember { mutableStateOf(TextFieldValue("")) }
    var keypadVisibility by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current

    Box(Modifier.fillMaxSize()){

        TextField(
            value = value,
            onValueChange = { newValue ->
                value = newValue
            },
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .onFocusChanged{ state ->
                    keypadVisibility = state.isFocused
                },
            readOnly = true,

        )

        AnimatedVisibility(
            visible = keypadVisibility,
            modifier = Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically(
                animationSpec = tween(durationMillis = 1000),
                initialOffsetY = { +it }
            ),
            exit = slideOutVertically(
                animationSpec = tween(durationMillis = 500),
                targetOffsetY = { +it }
            )
        ) {
            NumericKeypad(
                modifier = Modifier.fillMaxWidth().height(180.dp).padding(horizontal = 10.dp),
                typeDigit = { digit ->
                    val newText = when(digit){
                        "-" -> {
                            if (value.text.startsWith("-")) value.text.drop(1) else digit + value.text
                        }
                        DecimalFormatSymbols.getInstance().decimalSeparator.toString() -> {
                            if(DecimalFormatSymbols.getInstance().decimalSeparator.toString() !in value.text)
                                value.text + digit
                            else
                                value.text
                        }
                        "0" -> {
                            if(value.text.startsWith("0") && value.text.length == 1)
                                value.text
                            else
                                value.text + digit
                        }
                        else -> {
                            if(value.text.startsWith("0") && value.text.length == 1)
                                digit
                            else
                                value.text + digit
                        }
                    }
                    value = value.copy(
                        text = newText,
                        selection = TextRange(newText.length)
                    )
                },
                backspace = {
                    val newText = value.text.dropLast(1)
                    value = value.copy(
                        text = newText,
                        selection = TextRange(newText.length)
                    )
                },
                done = {
                    scope.launch {
                        Log.d("TESTTEST","Keypad Done")
//                        keypadVisibility = false
                        focusManager.clearFocus()
                    }

                }
            )
        }

    }


}