package com.chch.mycompose.ui.screen.selection

data class Topping(
    val id: Int,
    val name: String,
    val price: Int,
)

fun getToppingList(): List<Topping> {

    return listOf(
        Topping(1, "glass noodles", 2000),
        Topping(2, "Mushroom set", 3000),
        Topping(3, "10 quail eggs", 3000),
        Topping(4, "10 rice cakes", 1000),
        Topping(5, "10 Vienna sausages", 2000),
        Topping(6, "Bok choy", 2000),
    )
}
