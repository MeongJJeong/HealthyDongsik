package com.example.dongsik;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2, btn3;
    TextView sampleText;
    Intent intent;
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent(getApplicationContext(),ActiveActivity.class);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);
        btn3 = (Button)findViewById(R.id.btn3);
        sampleText = (TextView)findViewById(R.id.sampleText);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);


        calendarView = (CalendarView)findViewById(R.id.calendar);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                sampleText.setText(year + "년" + (month+1) + "월" + dayOfMonth + "일");
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (v == btn1){
            startActivity(intent);
//            overridePendingTransition();
        }
        if (v == btn2){
            Toast.makeText(getApplicationContext(),"동환이 바보",Toast.LENGTH_SHORT).show();
        }
        if (v == btn3){
            Toast.makeText(getApplicationContext(),"제주 삼다수",Toast.LENGTH_SHORT).show();
        }

    }
}
