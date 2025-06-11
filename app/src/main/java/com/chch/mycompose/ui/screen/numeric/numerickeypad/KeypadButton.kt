package com.chch.mycompose.ui.screen.numeric.numerickeypad

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ColumnScope.KeypadButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    content: @Composable RowScope.() -> Unit = {},
) {
    val shape = RoundedCornerShape(4.dp)
    Box(
        modifier = modifier.then(
            Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(bottom = 5.dp),
        ),
    ) {
        Button(
            modifier = Modifier
                .fillMaxSize(),
            shape = shape,
            onClick = onClick,
            content = content,
        )
    }
}

@Composable
fun ColumnScope.DigitButton(
    value: String,
    modifier: Modifier = Modifier,
    typeDigit: (String) -> Unit
) {
//    val inputService = LocalTextInputService.current as? NumericKeypadInputService
    KeypadButton(
        modifier,
        onClick = { typeDigit.invoke(value) },
    ) {
        Text(value, fontSize = 20.sp)
    }
}

@Composable
fun ColumnScope.IconButton(
    @DrawableRes iconResId: Int,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    KeypadButton(modifier, onClick) {
        Image(painter = painterResource(id = iconResId), contentDescription = label)
    }
}


@Composable
fun ColumnScope.IconButton(
    imageVector: ImageVector,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    KeypadButton(modifier, onClick) {
        Image(imageVector = imageVector, contentDescription = label)
    }
}