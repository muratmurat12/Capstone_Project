package com.example.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PoundsConverter extends AppCompatActivity {
        private Button button;
        private TextView textview2;
        private EditText editText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pounds_converter);
        button = findViewById(R.id.buttonSub);
        textview2 = findViewById(R.id.textViewCon);
        editText = findViewById(R.id.editTextCon);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                int kg = Integer.parseInt(s);
                double pound =  2.20462 * kg;
               // double pound = Math.round( 2.20462 * kg);
                textview2.setText("The Pound Value is" + pound);
                Toast.makeText(PoundsConverter.this, "The value is" + pound, Toast.LENGTH_SHORT).show();
            }
        });
    }
}