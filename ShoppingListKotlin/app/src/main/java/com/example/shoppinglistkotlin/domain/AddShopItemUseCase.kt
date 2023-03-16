package com.example.shoppinglistkotlin.domain

class AddShopItemUseCase (private val shopListRepository: ShopListRepository){

      fun addShopItem(shopItem: ShopItem){
        shopListRepository.addShopItem(shopItem)
    }
}