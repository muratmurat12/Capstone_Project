package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     private ActivityMainBinding binding;
    public ArrayList<ClipData.Item> itemArrayList;
    ItemAdaptor itemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}