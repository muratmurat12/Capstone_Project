package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.shoppinglist.databinding.ActivityAddItemBinding;

public class AddItemActivity extends AppCompatActivity {
    private ActivityAddItemBinding binding;

    SQLiteDatabase itemDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddItemBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        itemDatabase = this.openOrCreateDatabase("Items",MODE_PRIVATE,null);


    }

    public void addNewItem(View view){
        String name = binding.itemNameText.getText().toString();
        String price = binding.itemPriceText.getText().toString();
        String quantity = binding.itemQuantityText.getText().toString();
        CheckBox isFavorite = findViewById(R.id.isFavorite);
        itemDatabase.execSQL("CREATE TABLE IF NOT EXISTS items (id INTEGER PRIMARY KEY, name VARCHAR, Price VARCHAR, quantity VARCHAR, is_favorite INTEGER)");
        if (isFavorite.isChecked()){
            try {

                String insertStatement = "INSERT INTO items (name, price, quantity, is_favorite) VALUES(?, ?, ?, 1)";


                SQLiteStatement sqLiteStatement = itemDatabase.compileStatement(insertStatement);
                sqLiteStatement.bindString(1,name);
                sqLiteStatement.bindString(2,price);
                sqLiteStatement.bindString(3,quantity);
                sqLiteStatement.execute();
                Toast.makeText(AddItemActivity.this,"Added Successfully",Toast.LENGTH_SHORT).show();

            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try {

                String insertStatement = "INSERT INTO items (name, price, quantity, is_favorite) VALUES(?, ?, ?, 0)";


                SQLiteStatement sqLiteStatement = itemDatabase.compileStatement(insertStatement);
                sqLiteStatement.bindString(1,name);
                sqLiteStatement.bindString(2,price);
                sqLiteStatement.bindString(3,quantity);
                sqLiteStatement.execute();
                Toast.makeText(AddItemActivity.this,"Added Successfully",Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                e.printStackTrace();
            }
        }



        Intent intent = new Intent(AddItemActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

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
}
