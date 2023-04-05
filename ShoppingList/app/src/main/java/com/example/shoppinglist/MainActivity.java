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
import android.widget.TextView;
import android.widget.Toast;

import com.example.shoppinglist.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

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

            itemAdaptor.notifyDataSetChanged();
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

         if (item.getItemId() == R.id.map){
            Intent intent = new Intent(this,ShoppingMapsActivity.class);
            startActivity(intent);
        }
         else if (item.getItemId() == R.id.kg_pounds){
             Intent intent = new Intent(this,PoundsConverter.class);
             startActivity(intent);
         }
        else if (item.getItemId() == R.id.add_item){
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
        else if (item.getItemId() == R.id.signOutm){
                FirebaseAuth.getInstance().signOut();
                Intent intent =new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
        }

        return super.onOptionsItemSelected(item);
    }

    public void calculateTotal(View view){
        double totalCost = 0;
        try {
            SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT Price,quantity FROM items",null);

            int priceIx = cursor.getColumnIndex("Price");
            int quantityIx = cursor.getColumnIndex("quantity");

            while (cursor.moveToNext()){

                String price = cursor.getString(priceIx);
                String quantity = cursor.getString(quantityIx);

                double priceDouble = Double.parseDouble(price);
                double quantityDouble = Double.parseDouble(quantity);


                totalCost += (priceDouble*quantityDouble);

                binding.totalCost.setText("Total cost: " + totalCost);
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            try {
                SQLiteDatabase sqLiteDatabase = getApplicationContext().openOrCreateDatabase("Items",MODE_PRIVATE,null);
                int position  = itemArrayList.get(viewHolder.getAdapterPosition()).id;
                String deleteStatement = "DELETE FROM Items WHERE id = ?";
                SQLiteStatement sqLiteStatement = sqLiteDatabase.compileStatement(deleteStatement);
                sqLiteStatement.bindLong(1,position);
                sqLiteStatement.execute();
                itemAdaptor.notifyItemRemoved(position);
                itemArrayList.remove(viewHolder.getAdapterPosition());
                Toast.makeText(MainActivity.this,"Deleted Successfully",Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    };
}