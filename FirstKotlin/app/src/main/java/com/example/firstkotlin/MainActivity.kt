package com.example.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

        private lateinit var editTextName: EditText
        private lateinit var textViewName: TextView
        private lateinit var buttonSave: Button


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextName = findViewById(R.id.editTextName)
        textViewName = findViewById(R.id.textViewName)
        buttonSave =findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            val name = editTextName.text.toString().trim()
            textViewName.text = "Hello, $name"
        }
    }
}