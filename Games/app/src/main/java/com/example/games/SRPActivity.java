package com.example.games;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.games.databinding.ActivitySrpBinding;

import java.io.IOException;
import java.util.Random;

public class SRPActivity extends AppCompatActivity implements View.OnClickListener {

    ActivitySrpBinding binding;

    int img_hands[] = {R.drawable.gif_scissors, R.drawable.gif_rock, R.drawable.gif_paper};
    int win[][] = {{0,2},{1,0},{2,1}};

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

        int cnt = Integer.parseInt(binding.remainCnt.getText().toString());
        if(cnt == 0){

            finish();
        }
        else{

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
            winner(valUser, valRobot);

            // 4. 남은 횟수를 확인하고 0번이라면 최종 승리자 확인
            isGameOver();

        }
    }

    private void isGameOver() {
        int cnt = Integer.parseInt(binding.remainCnt.getText().toString()) - 1;
        binding.remainCnt.setText(Integer.toString(cnt));

        // 남은 횟수 0이라면 스코어를 비교, dialog 띄우기
        if(cnt == 0){
            // dialog 객체 "builder" 생성
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // 제목
            builder.setTitle("Game over");

            // 메시지
            int userScore = Integer.parseInt(binding.scoreUser.getText().toString());
            int robotScore = Integer.parseInt(binding.scoreRobot.getText().toString());

            if (userScore == robotScore)
                builder.setMessage("비겼습니다.");
            else if(userScore > robotScore)
                builder.setMessage("당신이 이겼습니다.");
            else
                builder.setMessage("로봇이 이겼습니다");

            // 버튼 만들기
            builder.setPositiveButton("새게임", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    binding.remainCnt.setText("10");
                    binding.scoreRobot.setText("0");
                    binding.scoreUser.setText("0");
                    binding.imgUserSelect.setImageResource(R.drawable.img_empty);
                    binding.imgRobotSelect.setImageResource(R.drawable.img_empty);

                }
            });

            builder.setNegativeButton("이제그만", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    SRPActivity.this.finish();
                }
            });

            builder.show();
        }
    }


    private void winner(int valUser, int valRobot) {

        int score = 0;

        // 비김 둘다 흔들기
        if(valUser == valRobot){
            moveGifImage(binding.imgUserSelect, img_hands[valUser]);
            moveGifImage(binding.imgRobotSelect, img_hands[valRobot]);
        }
        // 유저 이김
        else if((win[0][0] == valUser && win[0][1] == valRobot) ||
                (win[1][0] == valUser && win[1][1] == valRobot) ||
                (win[2][0] == valUser && win[2][1] == valRobot)) {

            score = Integer.parseInt(binding.scoreUser.getText().toString()) + 1;
            binding.scoreUser.setText(Integer.toString(score));
            moveGifImage(binding.imgUserSelect, img_hands[valUser]);
        }
        // 로봇 이김
        else{
            score = Integer.parseInt(binding.scoreRobot.getText().toString()) + 1;
            binding.scoreRobot.setText(Integer.toString(score));
            moveGifImage(binding.imgRobotSelect, img_hands[valRobot]);
        }
    }

    private int doRobot() {

        Random r = new Random();
        int num = r.nextInt(3);

        binding.imgRobotSelect.setImageResource(img_hands[num]);

        return num;
    }

    private void moveGifImage(ImageView imgView, int gif_res) {

        //안드로이드 버전이 pie 이상인 경우에만 동작시키는 코드, 28
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            // gif 이미지를 화면에 그릴수 있는 drawable 객체로 만드는 과정
            Drawable decodedAnimation = null;
            try {
                //디코딩할 gif 이미지를 가져옴. 디코딩
                ImageDecoder.Source source = ImageDecoder.createSource(getResources(), gif_res);
                decodedAnimation = ImageDecoder.decodeDrawable(source);

            } catch (IOException e) {   //이미지 디코딩이 도중에 멈추게 되면 발생할수 있는 예외처리
                e.printStackTrace();
            }
            // 디코딩된 drawable 이미지를 뷰에 그리기
            imgView.setImageDrawable(decodedAnimation);

            // gif 이미지 애니매이션 실행하기
            AnimatedImageDrawable ani = ((AnimatedImageDrawable) decodedAnimation);
            ani.setRepeatCount(1);
            ani.start();

        } else {
            imgView.setImageResource(gif_res);
        }
    }
}