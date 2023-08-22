package com.example.games;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.games.databinding.ActivitySrpBinding;

import java.util.Random;

public class SRPActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySrpBinding binding;

    int img_hands[] = {R.drawable.gif_scissors, R.drawable.gif_rock, R.drawable.gif_paper};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySrpBinding.inflate(getLayoutInflater()); // binding 클래스를 메모리에 올림
        setContentView(binding.getRoot()); // activity_srp.xml 정보 받음

        binding.btnScissors.setOnClickListener(this);
        binding.btnRock.setOnClickListener(this);
        binding.btnPaper.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // 1. 로봇이 가위바위보 중 어떤 것을 낼지 결정
        // 가위 = 0, 바위 = 1, 보 = 2
        int valRobot = doRobot();

        // 2. 사용자가 어떤 버튼을 클릭했는지 확인, 이미지 표시
        int valUser = 0;

        switch (view.getId()){
            case R.id.btn_scissors:
                valUser = 0;
                break;
            case R.id.btn_rock:
                valUser = 1;
                break;
            case R.id.btn_paper:
                valUser = 2;
                break;
        }

        binding.imgUserSelect.setImageResource(img_hands[valUser]);
        // 3. 누가 이겼는지 확인, 이긴 쪽의 이미지를 흔들고, 비기면 둘다 흔들어

        // 4. 남은 횟수를 확인하고 0번이라면 최종 승리자 확인

    }

    private int doRobot() {

        Random r = new Random();
        int num = r.nextInt(3);

        binding.imgRobotSelect.setImageResource(img_hands[num]);

        return num;
    }
}