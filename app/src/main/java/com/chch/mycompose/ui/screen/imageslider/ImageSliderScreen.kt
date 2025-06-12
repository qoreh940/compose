package com.chch.mycompose.ui.screen.imageslider

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.chch.mycompose.R


data class DrawableItem(
    val id : Int,
    @DrawableRes val resId: Int,
    val label: String = ""
)


@Composable
fun ImageSliderScreen(
    nc: NavController
) {
    var autoSliding by remember { mutableStateOf(false) }

    val items = listOf(
        DrawableItem(0, R.drawable.img_2790, "img_2790"),
        DrawableItem(1, R.drawable.img_2794, "img_2794"),
        DrawableItem(2, R.drawable.img_2795, "img_2795"),
        DrawableItem(3, R.drawable.img_2804, "img_2804"),
        DrawableItem(4, R.drawable.img_2810, "img_2810"),
        DrawableItem(5, R.drawable.img_2821, "img_2821"),
        DrawableItem(6, R.drawable.img_2840, "img_2840"),
        DrawableItem(7, R.drawable.img_2844, "img_2844"),
        DrawableItem(8, R.drawable.img_2847, "img_2847"),
        DrawableItem(9 ,R.drawable.img_2851, "img_2851"),
        DrawableItem(10, R.drawable.img_2886, "img_2886"),
        DrawableItem(11, R.drawable.img_2889, "img_2889"),
        DrawableItem(12, R.drawable.img_2890, "img_2890"),
        DrawableItem(13, R.drawable.img_2901, "img_2901"),
    )

    Column(
        Modifier.fillMaxSize()
    ) {
        ImageSlider(
            items = items,
            autoSliding = autoSliding
        )

        Spacer(Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Auto")
            Spacer(Modifier.width(5.dp))
            Switch(
                checked = autoSliding,
                onCheckedChange = {
                    autoSliding = it
                }
            )
        }

        Spacer(Modifier.height(20.dp))
        HorizontalDivider()
        Spacer(Modifier.height(20.dp))
        //Carousel
        Text("HorizontalMultiBrowseCarousel", modifier = Modifier.padding(horizontal = 20.dp))
        MultiCarousel(items)

    }

}