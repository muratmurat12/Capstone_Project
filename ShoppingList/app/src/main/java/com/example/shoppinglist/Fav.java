package com.example.shoppinglist;

public class Fav {
    int favId;
    String favName;
    String favPrice;
    String favQuantity;
    int isFav;

    public Fav(int favId, String favName, String favPrice, String favQuantity, int isFav) {
        this.favId = favId;
        this.favName = favName;
        this.favPrice = favPrice;
        this.favQuantity = favQuantity;
        this.isFav = isFav;
    }
}
