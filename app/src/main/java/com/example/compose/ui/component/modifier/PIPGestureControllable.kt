package com.example.compose.ui.component.modifier

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.awaitEachGesture
import androidx.compose.foundation.gestures.calculateCentroid
import androidx.compose.foundation.gestures.calculatePan
import androidx.compose.foundation.gestures.calculateZoom
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.changedToUp
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.input.pointer.util.VelocityTracker
import com.example.compose.ui.screen.pip.PIPDraggingInfo
import kotlinx.coroutines.launch
import kotlin.math.abs

@SuppressLint("ComposableNaming")
@Composable
fun Modifier.pipGestureInput(
    info: PIPDraggingInfo,
    startDrag: () -> Unit,
    drag: (Offset) -> Unit,
    endDrag: (Offset) -> Unit,
    scaleContent: (Float) -> Unit
): Modifier = composed {
    val scope = rememberCoroutineScope()
    val offset = rememberUpdatedState(info.offset)
    val contentWidth by rememberUpdatedState(newValue = info.currentContentWidth)
    val contentHeight by rememberUpdatedState(newValue = contentWidth * 9 / 16)
    val movableMaxHeight = info.movableMaxHeight
    val movableMaxWidth = info.movableMaxWidth
    val velocityTracker = remember { VelocityTracker() }

    var startOffset by remember { mutableStateOf(Offset.Zero) }

    this
        .pointerInput(Unit) {
            detectDragGestures(
                onDragStart = {
                    velocityTracker.resetTracking()
                    startOffset = offset.value
                    startDrag()
                },
                onDrag = { change, _ ->
                    velocityTracker.addPosition(change.uptimeMillis, change.position)
                    val newOffsetX = (offset.value.x + change.positionChange().x)
                    val newOffsetY = (offset.value.y + change.positionChange().y)
                    drag(Offset(newOffsetX, newOffsetY))
                    change.consume()
                },
                onDragEnd = {
                    val velocity = velocityTracker.calculateVelocity()
                    val middleOffsetY = movableMaxHeight / 2f - (contentHeight.toPx() / 2)
                    val yDirection = offset.value.y - startOffset.y
                    val xDirection = offset.value.x - startOffset.x
                    val minVelocity = 300f

                    val contentWidthPx = contentWidth.toPx()

                    val targetX: Float =
                        if (offset.value.x > movableMaxWidth - contentWidthPx + contentWidthPx / 3) {
//                            enableStartPreviewButton.invoke()
                            movableMaxWidth
                        } else if (offset.value.x < -contentWidthPx / 3) {
//                            enableEndPreviewButton.invoke()
                            -contentWidth.toPx()
                        } else if(abs(velocity.x) > minVelocity){
                            if(xDirection < 0f) 0f else movableMaxWidth - contentWidth.toPx()
                        } else if (offset.value.x + contentWidthPx / 2 > movableMaxWidth / 2) {
                            movableMaxWidth - contentWidth.toPx()
                        } else {
                            0f
                        }


                    val targetY: Float = when {
                        abs(velocity.y) > minVelocity  -> if (yDirection < 0) 0f else movableMaxHeight - contentHeight.toPx()
                        offset.value.y > middleOffsetY -> movableMaxHeight - contentHeight.toPx()
                        else -> 0f
                    }
                    endDrag(Offset(targetX, targetY))
                }
            )
        }
        .pointerInput(Unit) {
            var scale = 1f
            awaitEachGesture {
                do {
                    val event = awaitPointerEvent()
                    val isConsumed = event.changes.any { it.isConsumed }
                    if (event.changes.size == 2 && !isConsumed) {
                        val calcCentroid = event.calculateCentroid(useCurrent = true)
                        if (calcCentroid != Offset.Unspecified) {
                            if (event.type == PointerEventType.Release) {
                                val previewCenterX = offset.value.x + (contentWidth.toPx() / 2)
                                val previewCenterY =
                                    offset.value.y + (contentHeight.toPx() / 2)

                                val targetX =
                                    if (movableMaxWidth - previewCenterX < previewCenterX) {
                                        movableMaxWidth - contentWidth.toPx()
                                    } else {
                                        0f
                                    }

                                val targetY =
                                    if (movableMaxHeight - previewCenterY < previewCenterY) {
                                        movableMaxHeight - contentHeight.toPx()
                                    } else {
                                        0f
                                    }
                                scope.launch { endDrag(Offset(targetX, targetY)) }
                            } else {
                                val calPan = event.calculatePan()
                                val prevOffset = offset.value
                                val calcScale = event.calculateZoom()
                                scale = (scale * calcScale).coerceIn(.5f, 1f)

                                val newOffset = prevOffset + calPan
                                scaleContent(scale)
                                drag(newOffset)
                            }
                            event.changes.forEach { it.consume() }
                        }
                    }

                } while (event.changes.any { it.pressed || it.changedToUp() })
            }
        }
}