package com.example.shoppinglist;

public class Item {
    int id;
    String name;
    String price;
    String quantity;
    int isFavorite;

    public Item(int id, String name, String price, String quantity, int isFavorite) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isFavorite = isFavorite;
    }
}

