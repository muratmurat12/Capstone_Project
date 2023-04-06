package com.example.shoppinglist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class Calen extends AppCompatActivity {

    CalendarView calendarView;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calen);

         setContentView(R.layout.activity_calen);
         calendarView = findViewById(R.id.calenderView);
         calendar = Calendar.getInstance();

         setDate(5,4,2023);

         getDate();
         calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                 Toast.makeText(Calen.this, day+"/"+month+1+"/"+year+"/", Toast.LENGTH_SHORT).show();
             }
         });
    }
    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
        Toast.makeText(this,selected_date, Toast.LENGTH_SHORT).show();
    }

    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR,year);
        calendar.set(Calendar.MONTH,month -1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli =calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
}