package com.chch.mycompose.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BorderedSection(
    modifier: Modifier = Modifier,
    title: String,
    content: @Composable BoxScope.() -> Unit,
) {
    Box(
        Modifier.padding(15.dp)
    ) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(10.dp))
                .padding(16.dp)
        ) {
            content()
        }

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(horizontal = 8.dp)
                .align(Alignment.TopCenter)
                .offset(y = (-8).dp)
        )

    }

}