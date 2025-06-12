package com.chch.mycompose.ui.screen.imageslider

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiCarousel(
    items: List<DrawableItem>
){
    HorizontalMultiBrowseCarousel(
        state = rememberCarouselState { items.size },
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        preferredItemWidth = 240.dp,
        itemSpacing = 10.dp,
        contentPadding = PaddingValues(16.dp)
    ) { index ->

        Image(
            modifier = Modifier.height(240.dp).maskClip(MaterialTheme.shapes.extraLarge),
            painter = painterResource(items[index].resId),
            contentDescription = items[index].label,
            contentScale = ContentScale.FillHeight
        )
    }


}