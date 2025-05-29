package com.example.compose.ui.screen.pip

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.Dp

data class PIPDraggingInfo(
    val offset: Offset,
    val movableMaxWidth : Float,
    val movableMaxHeight : Float,
    val currentContentWidth: Dp,
)
