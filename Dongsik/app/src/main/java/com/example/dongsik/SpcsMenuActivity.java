package com.example.dongsik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dongsik.adapter.SpcsMenuAdapter;
import com.example.dongsik.dbHelper.SpcsDbHelper;

import java.util.ArrayList;

public class SpcsMenuActivity extends AppCompatActivity {

    SpcsDbHelper dbHelper;
    SQLiteDatabase sqLiteDB;
    Cursor cursor;

    RecyclerView recyclerView;
    SpcsMenuAdapter spcsMenuAdapter;

    Button btn_add;
    EditText edt_add;
    ArrayList<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spcs_menu);
        list = new ArrayList<>();

        dataRefresh();

        btn_add = (Button)findViewById(R.id.btn_add);
        edt_add = (EditText)findViewById(R.id.edt_add);
        recyclerView = (RecyclerView)findViewById(R.id.spcs_recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.supportsPredictiveItemAnimations();
        recyclerView.setLayoutManager(layoutManager);

        spcsMenuAdapter = new SpcsMenuAdapter(list,this);
        recyclerView.setAdapter(spcsMenuAdapter);

        spcsMenuAdapter.setOnItemClicklistener(new SpcsMenuAdapter.OnItemClicklistener() {
            @Override
            public void onItemClick(View v, int pos) {
                Intent intent = new Intent(SpcsMenuActivity.this,ActiveActivity.class);
                intent.putExtra("spcs",list.get(pos));
                setResult(RESULT_OK,intent);
                finish();

                Toast.makeText(getApplicationContext(),list.get(pos),Toast.LENGTH_SHORT).show();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_add.length() != 0){
                    dataAdd(edt_add.getText().toString());
                    edt_add.setText(null);
                } else {
                    Toast.makeText(getApplicationContext(),"바르게 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void dataAdd(String data){
        dbHelper = new SpcsDbHelper(this);
        sqLiteDB = dbHelper.getWritableDatabase();
        sqLiteDB.execSQL("INSERT INTO spcsTbl VALUES ('"
                        + data +"');");
        sqLiteDB.close();
        list.add(data);
        spcsMenuAdapter.notifyDataSetChanged();

        Toast.makeText(getApplicationContext(),"입력됨", Toast.LENGTH_SHORT).show();

    }

    private void dataRefresh (){
        dbHelper = new SpcsDbHelper(this);
        Cursor cursor;
        sqLiteDB = dbHelper.getWritableDatabase();
        cursor = sqLiteDB.rawQuery("SELECT * FROM spcsTbl;",null);

        while (cursor.moveToNext()){
            list.add(cursor.getString(0));
        }
        cursor.close();
        sqLiteDB.close();
    }

    @Override
    public void finish() {
        super.finish();
    }
}