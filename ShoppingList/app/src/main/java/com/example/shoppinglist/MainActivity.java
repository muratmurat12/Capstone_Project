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
        public void getData(){
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM items",null);
            int idIx = cursor.getColumnIndex("id");
            int nameIx = cursor.getColumnIndex("name");
            int priceIx = cursor.getColumnIndex("Price");
            int quantityIx = cursor.getColumnIndex("quantity");
            int isFavoriteIx = cursor.getColumnIndex("is_favorite");

              while (cursor.moveToNext()){
                int id = cursor.getInt(idIx);
                String name = cursor.getString(nameIx);
                String price = cursor.getString(priceIx);
                String quantity = cursor.getString(quantityIx);
                int isFavorite = cursor.getInt(isFavoriteIx);
                Item item = new Item(id,name,price,quantity,isFavorite);
                itemArrayList.add(item);
            }
}