package com.example.dongsik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.example.dongsik.adapter.ChronoViewAdapter;
import com.example.dongsik.database.Dictionary;

import java.util.ArrayList;

public class ActiveActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_switcher, bChrono_stop , bChrono_start, bChrono_reset;
    ViewSwitcher viewSwitcher,btn_viewSwitcher;
    Chronometer chronometer;
    Button spcs_text;

    RecyclerView recyclerView;
    ChronoViewAdapter adapter;
    LinearLayout btn_layout;

    ArrayList<Dictionary> list;
    private int count = 0;
    private boolean isStop = false;
    private long pauseOffset;

    private long interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active);

        btn_switcher = (Button)findViewById(R.id.active_btn_switcher);
        bChrono_start = (Button) findViewById(R.id.chrono_btn_start);
        bChrono_stop = (Button)findViewById(R.id.chrono_btn_stop);
        bChrono_reset = (Button)findViewById(R.id.chrono_btn_reset);
        viewSwitcher = (ViewSwitcher)findViewById(R.id.active_viewSwitcher);
        btn_viewSwitcher = (ViewSwitcher)findViewById(R.id.flipper_btnFlip);
        spcs_text = (Button) findViewById(R.id.spcs_text);

        btn_layout = findViewById(R.id.activity_linear_btn);

        recyclerView = findViewById(R.id.list_chrono);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        adapter = new ChronoViewAdapter(list,this);
        recyclerView.setAdapter(adapter);

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                layoutManager.getOrientation());
//        recyclerView.addItemDecoration(dividerItemDecoration);


        btn_switcher.setOnClickListener(this);
        bChrono_stop.setOnClickListener(this);
        bChrono_start.setOnClickListener(this);
        bChrono_reset.setOnClickListener(this);
        spcs_text.setOnClickListener(this);

        chronometer = (Chronometer)findViewById(R.id.flipper_chrono);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.active_btn_switcher:
                viewSwitcher.showNext();
                break;

            case R.id.chrono_btn_start:
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                btn_viewSwitcher.showNext();
                btn_layout.setVisibility(View.VISIBLE);
                bChrono_start.setVisibility(View.INVISIBLE);
                break;

            case R.id.chrono_btn_stop:
                if (!isStop){
                    chronometer.stop();
                    pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
                    bChrono_stop.setText("계속");
                    bChrono_reset.setText("초기화");
                    isStop = true;
                }else{
                    chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                    chronometer.start();
                    bChrono_stop.setText("정지");
                    bChrono_reset.setText("기록");
                    isStop = false;
                }
                break;

            case R.id.chrono_btn_reset:
                if (!isStop){
                    interval = chronometer.getBase();
                    count++;
                    Dictionary data = new Dictionary(count,chronometer.getBase() - interval + "",chronometer.getContentDescription()+"");
                    list.add(data);
                    adapter.notifyDataSetChanged();

                }else {
                    chronometer.stop();
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    btn_viewSwitcher.showNext();
                    bChrono_stop.setText("정지");
                    bChrono_reset.setText("기록");
                    isStop = false;
                    btn_layout.setVisibility(View.INVISIBLE);
                    bChrono_start.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.spcs_text:
                Intent intent = new Intent(getApplicationContext(),SpcsMenuActivity.class);
                intent.putExtra("spcs",spcs_text.getText().toString());
                startActivityForResult(intent,0);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK){
            String spcs = data.getStringExtra("spcs");
            spcs_text.setText(spcs);

        }
    }
}
