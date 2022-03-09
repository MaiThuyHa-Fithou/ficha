package com.mtha.myquizgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tvHello, tvCauHoi;
    RadioButton rdA, rdB, rdC, rdD;
    Button btnNext, btnExit;
    RadioGroup rdGroup;

    QuizGameDB quizGameDB = new QuizGameDB(QuestionActivity.this);;
    ArrayList<CauHoi> lsCauHoi = new ArrayList<>();
    ArrayList<DapAn> lsDapAn = new ArrayList<>();
    ArrayList<String> lsDapAnDung = new ArrayList<>();
    long count =0; int flag =0; int index = 0;
    public static int marks=0,correct=0,wrong=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        getViews();
        Intent intent = getIntent();
        tvHello.setText(tvHello.getText().toString() +" " + intent.getStringExtra("user"));
        count = quizGameDB.getCountCauHoi();
        getListCauHoi();
        setViews();

    }

    private void setViews(){
        flag = lsCauHoi.get(index).getCauhoi_id();
        getListDapAn(flag);

        CauHoi cauHoi = quizGameDB.getCauHoiByID(flag);
        tvCauHoi.setText(cauHoi.getCauhoi_noidung());
        rdA.setText(lsDapAn.get(0).getDapan_noidung());
        rdB.setText(lsDapAn.get(1).getDapan_noidung());
        rdC.setText(lsDapAn.get(2).getDapan_noidung());
        rdD.setText(lsDapAn.get(3).getDapan_noidung());
    }
    private void getListCauHoi(){
        lsCauHoi = quizGameDB.getAllCauHoi();
    }
    private void getListDapAn(int cauHoiID){
        lsDapAn = quizGameDB.getAllDapAn(cauHoiID);
    }

    private void getListDapAnDung(int cauHoiID){
       lsDapAnDung= quizGameDB.getListDapAnDung(cauHoiID);
    }
    private void getViews(){
        tvHello = findViewById(R.id.tvHello);
        tvCauHoi = findViewById(R.id.tvCauHoi);
        rdA = findViewById(R.id.rdA);
        rdB = findViewById(R.id.rdB);
        rdC = findViewById(R.id.rdC);
        rdD = findViewById(R.id.rdD);
        rdGroup = findViewById(R.id.rdgDapAn);
        btnNext = findViewById(R.id.btnNext);
        btnExit = findViewById(R.id.btnExit);
        btnNext.setOnClickListener(this::onClick);
        btnExit.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnNext:
                xuLyCauHoi();
                break;
            case R.id.btnStop:
                break;
        }
    }
    private void xuLyCauHoi(){
        getListDapAnDung(flag);
        if(rdGroup.getCheckedRadioButtonId()==-1){
            Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
            return;
        }
        RadioButton radioButton = findViewById(rdGroup.getCheckedRadioButtonId());
        String dapAn = radioButton.getText().toString();
        if(lsDapAnDung.contains(dapAn)){
            correct++;
            Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
        }else {
            wrong++;
            Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }
        index++;
        if(index<lsCauHoi.size()){
            setViews();
        }else {

        }
        rdGroup.clearCheck();
    }
}