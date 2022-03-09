package com.mtha.myquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public final String TAG ="MainActivity";
    String userName="";
    EditText edtName;
    Button btnStart, btnStop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();
        /*
        QuizGameDB db = new QuizGameDB(MainActivity.this);
        CauHoi cauHoi = new CauHoi("Ha Noi mua mua");

        db.insCauHoi(cauHoi);
        DapAn dapAn = new DapAn("co mua",1,2);
        DapAn dapAn1 = new DapAn("co nang",0,2);
        DapAn dapAn2 = new DapAn("co dong",0,2);
        DapAn dapAn3 = new DapAn("co tuyet",0,2);
        db.insDapAn(dapAn);
        db.insDapAn(dapAn1);
        db.insDapAn(dapAn2);
        db.insDapAn(dapAn3);
        DapAn dapAn4 = new DapAn("thu 5",0,1);
        DapAn dapAn5 = new DapAn("thu 6",0,1);
        db.insDapAn(dapAn4);
        db.insDapAn(dapAn5);

         */
    }

    private void getViews(){
        edtName = findViewById(R.id.edtName);
        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnStart.setOnClickListener(this::onClick);
        btnStop.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                userName = edtName.getText().toString();
                Intent intent = new Intent(MainActivity.this, QuestionActivity.class);
                intent.putExtra("user", userName);
                startActivity(intent);
                break;
            case R.id.btnStop:
                System.exit(0);
                break;
        }
    }
}