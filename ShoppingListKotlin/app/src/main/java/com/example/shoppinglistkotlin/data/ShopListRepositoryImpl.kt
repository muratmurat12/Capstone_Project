package com.example.shoppinglistkotlin.data

import com.example.shoppinglistkotlin.domain.ShopItem
import com.example.shoppinglistkotlin.domain.ShopListRepository

object ShopListRepositoryImpl: ShopListRepository {

    private val shopList = mutableListOf<ShopItem>()

    private var autoIncrementID =0

    override fun addShopItem(shopItem: ShopItem) {
       if (shopItem.id == ShopItem.UNDEFINED_ID){
        shopItem.id = autoIncrementID
        autoIncrementID++
       }
        shopList.add(shopItem)
    }

    override fun deleteShopItem(ShopItem: ShopItem) {
       shopList.remove(ShopItem)
    }

    override fun editShopItem(shopItem: ShopItem) {
        val oldElement = getShopItem(shopItem.id)
        shopList.remove(oldElement)
        addShopItem(shopItem)
    }

   override fun getShopItem(shopItemId: Int): ShopItem {
        return shopList.find {
            it.id == shopItemId
        } ?: throw RuntimeException("Element with id $shopItemId not found")
    }

    override fun getShopList(): List<ShopItem> {
        return shopList.toList()
    }

}