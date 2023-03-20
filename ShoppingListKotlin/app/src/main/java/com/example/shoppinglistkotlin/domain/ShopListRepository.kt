package com.example.shoppinglistkotlin.domain

import androidx.lifecycle.LiveData

interface ShopListRepository {

    fun addShopItem(shopItem: ShopItem)

    fun deleteShopItem(ShopItem: ShopItem)

    fun editShopItem(shopItem: ShopItem)

    fun getShopItem(shopItemId:Int): ShopItem

    fun getShopList(): LiveData<List<ShopItem>>

}