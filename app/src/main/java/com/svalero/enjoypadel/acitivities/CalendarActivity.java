package com.svalero.enjoypadel.acitivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CalendarView;

import com.svalero.enjoypadel.R;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        mCalendarView = findViewById(R.id.calendar);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + month + "/" + year;

                Intent intent = new Intent(CalendarActivity.this, AddMatchActivity.class);
                intent.putExtra("date", date);
                startActivity(intent);
                finish();
            }
        });
    }
}