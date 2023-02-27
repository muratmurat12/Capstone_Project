package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    public ArrayList<Item> itemArrayList;
    ItemAdaptor itemAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        itemArrayList = new ArrayList<>();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemAdaptor = new ItemAdaptor(itemArrayList);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(binding.recyclerView);
        binding.recyclerView.setAdapter(itemAdaptor);
        getData();

    }
}