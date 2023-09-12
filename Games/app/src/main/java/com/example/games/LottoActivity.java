package com.example.games;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.games.databinding.ActivityLottoBinding;

import java.util.ArrayList;
import java.util.Random;

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
        if(!binding.switch1.isChecked()) return;
        if(i == SCROLL_STATE_IDLE) addOneNumber();
    }

    private void addOneNumber() {
        if(myNumList.size() == 6){
            Toast.makeText(getApplicationContext(), "6개를 모두 선택했습니다.", Toast.LENGTH_SHORT).show();
            binding.numberPicker.setEnabled(false);
            return;
        }

        int pickNumber = binding.numberPicker.getValue();
        TextView tempTextView;

        if(myNumList.contains(pickNumber)){
            Toast.makeText(getApplicationContext(), "숫자가 중복됩니다.", Toast.LENGTH_SHORT).show();
        }
        else{
            myNumList.add(pickNumber);
            tempTextView = myNumTextView.get(myNumList.size() - 1);
            tempTextView.setText(Integer.toString(pickNumber));
            tempTextView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addButton:
                if(binding.switch1.isChecked()) return;
                addOneNumber();
                break;
            case R.id.clearButton:
                clearNumbers();
                break;
            case R.id.runButton:
                addRandomNumber();
                winCount();
                break;
        }
    }

    private void winCount() {
        int count = 0;
        String msg = "당첨숫자 : ";

        for (Integer myNum : myNumList) {
            if(lottoNumList.contains(myNum)){
                count++;
                msg += myNum + " ";
            }
        }
        binding.txtResult.setText(msg + "\n당첨개수 : " + count);
    }

    private void addRandomNumber() {
        // 주작 금지. 사용자 6개 뽑아야 추첨 가능
        if(myNumList.size() != 6){
            Toast.makeText(getApplicationContext(), "6개의 숫자를 선택해야 추첨할 수 있습니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        // 로또 번호가 6개면 초기화하기
        if(lottoNumList.size() == 6) lottoNumList.clear();

        int randomNumber = 0;
        TextView tempTextView;
        Random r = new Random();

        while(lottoNumList.size() < 6){
            randomNumber = r.nextInt(45) + 1;

            if(!lottoNumList.contains(randomNumber)){
                lottoNumList.add(randomNumber);
                tempTextView = lottoNumTextView.get(lottoNumList.size() - 1);
                tempTextView.setText(Integer.toString(randomNumber));
                tempTextView.setVisibility(View.VISIBLE);
            }
        }
    }

    private void clearNumbers() {
        // TextView들의 visibility 조정
        // 넘버리스트 비우기
        for (TextView tv : myNumTextView) {
            tv.setVisibility(View.GONE);
        }
        for (TextView t : lottoNumTextView) {
            t.setVisibility(View.GONE);
        }
        myNumList.clear();
        lottoNumList.clear();

        binding.numberPicker.setEnabled(true);
        binding.txtResult.setText("6개의 숫자를 선택하세요");
    }
}