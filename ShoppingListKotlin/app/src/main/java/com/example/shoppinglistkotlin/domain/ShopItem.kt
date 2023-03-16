package com.example.shoppinglistkotlin.domain

data class ShopItem(
    val id:Int,
    val name: String,
    val count: Int,
    val enable: Boolean
)
