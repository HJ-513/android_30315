package com.example.games;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_game1).setOnClickListener(this);
        findViewById(R.id.btn_game2).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btn_game1:
                //가위바위보 실행
                Intent i1 = new Intent(this, SRPActivity.class);
                startActivity(i1);
                break;
            case R.id.btn_game2:
                //가위바위보 실행
                Intent i2 = new Intent(this, LottoActivity.class);
                startActivity(i2);
                break;
        }
    }
}