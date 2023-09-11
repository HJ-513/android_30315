package com.example.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.games.databinding.ActivityLottoBinding;

import java.util.ArrayList;

public class LottoActivity extends AppCompatActivity implements NumberPicker.OnScrollListener, View.OnClickListener {

    ActivityLottoBinding binding;

    // 자료구조 생성
    ArrayList<Integer> myNumList = new ArrayList<>();
    ArrayList<TextView> myNumTextView = new ArrayList<>();

    ArrayList<Integer> lottoNumList = new ArrayList<>();
    ArrayList<TextView> lottoNumTextView = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLottoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //NumberPicker setting
        binding.numberPicker.setMaxValue(45);
        binding.numberPicker.setMinValue(1);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            binding.numberPicker.setTextSize(70.0f);
        }
        binding.numberPicker.setOnScrollListener(this);

        //버튼 3개 Listener 달기
        binding.addButton.setOnClickListener(this);
        binding.clearButton.setOnClickListener(this);
        binding.runButton.setOnClickListener(this);

        //textView를 리스트에 넣기
        myNumTextView.add(binding.myNum1);
        myNumTextView.add(binding.myNum2);
        myNumTextView.add(binding.myNum3);
        myNumTextView.add(binding.myNum4);
        myNumTextView.add(binding.myNum5);
        myNumTextView.add(binding.myNum6);

        lottoNumTextView.add(binding.lottoNum1);
        lottoNumTextView.add(binding.lottoNum2);
        lottoNumTextView.add(binding.lottoNum3);
        lottoNumTextView.add(binding.lottoNum4);
        lottoNumTextView.add(binding.lottoNum5);
        lottoNumTextView.add(binding.lottoNum6);
    }

    @Override
    public void onScrollStateChange(NumberPicker numberPicker, int i) {

    }

    @Override
    public void onClick(View view) {

    }
}