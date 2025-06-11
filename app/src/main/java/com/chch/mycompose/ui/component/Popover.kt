package com.chch.mycompose.ui.component

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.PaintingStyle
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawOutline
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider

@Composable
fun PopupExample(
    onDismissRequest: () -> Unit,
    context: String
){
    Box {
        val popupHeight = 50.dp
        val cornerSize = 16.dp

        Popup(
            onDismissRequest = onDismissRequest,
            offset = IntOffset(0, -50.dp.value.toInt()),
            alignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .height(popupHeight)
                    .padding(horizontal = 10.dp)
                    .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(cornerSize))
            ){
                Column(
                    modifier = Modifier.fillMaxHeight().padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = context)
                }


            }
        }
    }
}

@Composable
fun RowPopover(
    items: List<PopoverItem>,
    onDismissRequest: () -> Unit
) {

    if (items.isEmpty()) return

    var popupSize by remember {
        mutableStateOf(IntSize.Zero)
    }
    val backgroundColor = MaterialTheme.colorScheme.primaryContainer

    BackHandler {
        onDismissRequest.invoke()
    }

    Popup(
        popupPositionProvider = AnchorCenterPopupPositionProvider(),
        onDismissRequest = onDismissRequest
    ) {

        Column(
            modifier = Modifier
                .height(45.5.dp)
                .onSizeChanged {
                    popupSize = it
                }
        ) {
            // Items
            Box(
                Modifier
                    .height(38.dp)
                    .background(
                        color = backgroundColor,
                        RoundedCornerShape(5.dp)
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    for (index in items.indices) {
                        Box(
                            modifier = Modifier
                                .clickable { items[index].onClick.invoke() }) {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp),
                                text = items[index].label,
                                color = MaterialTheme.colorScheme.primary,
                                textAlign = TextAlign.Center,
                            )
                        }


                        if (items.size > 1 && index < items.size - 1) {
                            Spacer(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .width(1.dp)
                                    .background(MaterialTheme.colorScheme.onPrimary)
                            )
                        }
                    }


                }
            }

            //Draw Anchor
            Box(
                modifier = Modifier
                    .drawBehind {
                        val width = 12.dp.toPx()
                        val height = 7.5.dp.toPx()
                        val popupWidth = popupSize.width.dp.value
                        val startX = popupWidth / 2
                        val inputPortPath = Path().apply {
                            moveTo(startX, 0f)
                            lineTo(startX - width / 2, 0f)
                            lineTo(startX, height)
                            lineTo(startX + width / 2, 0f)
                            close()
                        }

                        drawIntoCanvas { c ->
                            c.drawOutline(
                                outline = Outline.Generic(inputPortPath),
                                paint = Paint().apply {
                                    this.color = backgroundColor
                                    this.style = PaintingStyle.Fill
                                    this.strokeWidth = strokeWidth
                                    pathEffect = PathEffect.cornerPathEffect(1.dp.toPx())
                                }
                            )

                        }
                    }
            )
        }

    }
}

class AnchorCenterPopupPositionProvider(
    private val x: Int = 0,
    private val y: Int = 0
) : PopupPositionProvider {
    override fun calculatePosition(
        anchorBounds: IntRect,
        windowSize: IntSize,
        layoutDirection: LayoutDirection,
        popupContentSize: IntSize
    ): IntOffset {

        val yPosition = if (anchorBounds.top > popupContentSize.height) {
            anchorBounds.top - popupContentSize.height
        } else {
            anchorBounds.bottom
        }

        return IntOffset(
            (anchorBounds.left + (anchorBounds.width / 2) - (popupContentSize.width / 2)),
            yPosition
        )
    }
}

data class PopoverItem(
    val label: String,
    val onClick: () -> Unit
)
