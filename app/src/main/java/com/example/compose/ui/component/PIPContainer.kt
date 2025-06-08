package com.example.compose.ui.component

import android.annotation.SuppressLint
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import com.example.compose.ui.component.modifier.pipGestureInput
import com.example.compose.ui.screen.pip.PIPDraggingInfo
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@SuppressLint("UnusedBoxWithConstraintsScope")
@Composable
fun PIPContainer(
    maxWidth: Dp, // content의 maxWidth, 보통 Screen Width
    content: @Composable () -> Unit
) {
    val scope = rememberCoroutineScope()
    val offset = remember { Animatable(Offset(0f, 0f), Offset.VectorConverter) }
    var contentWidth by remember { mutableStateOf(maxWidth) }

    fun startDrag() {
        scope.launch {
            offset.stop()
        }
    }

    fun drag(newOffset: Offset) {
        scope.launch {
            offset.snapTo(newOffset)
        }
    }

    fun endDrag(newOffset: Offset) {
        scope.launch {
            offset.animateTo(
                targetValue = newOffset,
                animationSpec = tween(
                    durationMillis = 300,
                    easing = FastOutLinearInEasing
                )
//                    spring(
//                    dampingRatio = Spring.DampingRatioMediumBouncy,
//                    stiffness = Spring.StiffnessMedium,
//                )
            )
        }
    }

    // TODO : hide buttons
    BoxWithConstraints(Modifier.fillMaxSize()) {
        val movableMaxWidth = with(LocalDensity.current) { this@BoxWithConstraints.maxWidth.toPx() }
        val movableMaxHeight = with(LocalDensity.current) { maxHeight.toPx() }

        val info = PIPDraggingInfo(
            offset = offset.value,
            movableMaxHeight = movableMaxHeight,
            movableMaxWidth = movableMaxWidth,
            currentContentWidth = contentWidth,
        )
        Box(
            modifier = Modifier
                .offset {
                    IntOffset(
                        x = offset.value.x.roundToInt(),
                        y = offset.value.y.roundToInt()
                    )
                }
                .pipGestureInput(
                    info = info,
                    startDrag = ::startDrag,
                    drag = ::drag,
                    endDrag = ::endDrag,
                    scaleContent = {
                        contentWidth = maxWidth * it
                    }
                )
                .width(contentWidth)
                .aspectRatio(16f / 9f)
                .background(MaterialTheme.colorScheme.surfaceContainer)
        ) {
            content()
        }
    }

}