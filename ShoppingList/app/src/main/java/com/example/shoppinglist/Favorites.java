package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.shoppinglist.databinding.ActivityFavoritesBinding;

import java.util.ArrayList;

public class Favorites extends AppCompatActivity {
    private ActivityFavoritesBinding binding;
    ArrayList<Fav> favArrayList;
    FavAdaptor favAdaptor;
    ItemAdaptor itemAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        favArrayList = new ArrayList<>();
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(binding.recyclerViewFavorites);
        binding.recyclerViewFavorites.setLayoutManager(new LinearLayoutManager(this));
        favAdaptor = new FavAdaptor(favArrayList);
        binding.recyclerViewFavorites.setAdapter(favAdaptor);
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
                if (isFavorite == 1){
                    Fav fav= new Fav(id,name,price,quantity,isFavorite);
                    favArrayList.add(fav);
                }
            }
            favAdaptor.notifyDataSetChanged();
            cursor.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.shopping_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.add_item){
            Intent intent = new Intent(this,AddItemActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.favoritesPage){
            Intent intent = new Intent(this,Favorites.class);
            startActivity(intent);
        }
        else if (item.getItemId() == R.id.shoppingList){
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {


        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            try {
                SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("Items", MODE_PRIVATE, null);
                String favName = favArrayList.get(viewHolder.getAdapterPosition()).favName;
                String favPrice = favArrayList.get(viewHolder.getAdapterPosition()).favPrice;
                String favQuantity = favArrayList.get(viewHolder.getAdapterPosition()).favQuantity;

                String insertStatement = "INSERT INTO items (name, price, quantity, is_favorite) VALUES(?, ?, ?, 0)";
                SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(insertStatement);
                sqLiteStatement.bindString(1,favName);
                sqLiteStatement.bindString(2,favPrice);
                sqLiteStatement.bindString(3,favQuantity);
                sqLiteStatement.execute();
                favAdaptor.notifyDataSetChanged();
                itemAdaptor.notifyDataSetChanged();
                Toast.makeText(Favorites.this,"Added Successfully",Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                e.printStackTrace();
            }


        }
    };


}
