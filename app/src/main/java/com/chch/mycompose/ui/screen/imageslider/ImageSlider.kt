package com.chch.mycompose.ui.screen.imageslider

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class DrawableItem(
    @DrawableRes val resId: Int,
    val label: String = ""
)

@Composable
fun ImageSlider(
    items: List<DrawableItem>,
    autoSliding: Boolean = false
) {
    val pageState = rememberPagerState { items.size }
    val scope = rememberCoroutineScope()

    LaunchedEffect(autoSliding) {
        while (autoSliding && isActive) {
            delay(3000L)
            val nextPage = (pageState.currentPage + 1) % items.size
            pageState.animateScrollToPage(nextPage)
        }
    }

    Box(
        Modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        HorizontalPager(
            state = pageState,
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceDim)
        ) { page ->
            Box(
                Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(items[page].resId),
                    contentDescription = items[page].label,
                    contentScale = ContentScale.Inside
                )
            }


        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(items.size) { index ->
                val isSelected = pageState.currentPage == index
                Box(
                    modifier = Modifier
                        .size(5.dp)
                        .background(
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.inversePrimary,
                            shape = CircleShape
                        )
                )
                Spacer(Modifier.width(4.dp))
            }
        }

        IconButton(
            onClick = {
                val prevPage = (pageState.currentPage - 1 + items.size) % items.size
                scope.launch {
                    pageState.animateScrollToPage(prevPage)
                }
            },
            enabled = !autoSliding,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "prev",
                tint = if (!autoSliding) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        }

        IconButton(
            onClick = {
                val nextPage = (pageState.currentPage + 1) % items.size
                scope.launch {
                    pageState.animateScrollToPage(nextPage)
                }
            },
            enabled = !autoSliding,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = "next",
                tint = if (!autoSliding) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
            )
        }
    }


}


















