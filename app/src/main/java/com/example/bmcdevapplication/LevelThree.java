package com.example.bmcdevapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelThree extends AppCompatActivity {

    Button btn_Start3;
    Button btn_Answer9;
    Button btn_Answer10;
    Button btn_Answer11;
    Button btn_Answer12;
    TextView tv_Question3;
    TextView tv_MessageBar3;
    TextView tv_Score3;
    TextView tv_Timer2;

    MediaPlayer player;

    Game g;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_three);
        defineButtons();

        btn_Start3 = findViewById(R.id.btn_Start3);
        btn_Answer9 = findViewById(R.id.btn_Answer5);
        btn_Answer10 = findViewById(R.id.btn_Answer6);
        btn_Answer11 = findViewById(R.id.btn_Answer7);
        btn_Answer12 = findViewById(R.id.btn_Answer8);

        tv_Score3 = findViewById(R.id.tv_Score3);
        tv_MessageBar3 = findViewById(R.id.tv_MessageBar3);
        tv_Question3 = findViewById(R.id.tv_Question3);
        tv_Timer2 = findViewById(R.id.tv_Timer2);

        tv_Question3.setText("");
        tv_MessageBar3.setText("Press start game to play");
        tv_Score3.setText("0 Points");
        tv_Timer2.setText("  seconds remaining");

        //If clicked start button goes invisible and game begins
        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                g = new Game();
                g.setThisQuestion(0);
                startCountDown();
                nextQuestion();
            }
        };

        btn_Start3.setOnClickListener(startButtonClickListener);

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {

            //Displays total amount of questions
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                if (g.checkAnswer(answerSelected)) {
                    countDownTimer.cancel();
                    playCorrect();
                    anotherQuestion();
                } else {
                    countDownTimer.cancel();
                    anotherQuestion();
                }
            }
        };
        btn_Answer9.setOnClickListener(answerButtonClickListener);
        btn_Answer10.setOnClickListener(answerButtonClickListener);
        btn_Answer11.setOnClickListener(answerButtonClickListener);
        btn_Answer12.setOnClickListener(answerButtonClickListener);
    }

    private void anotherQuestion() {
        tv_Score3.setText(Integer.toString(g.getScore()));
        g.setThisQuestion(g.getThisQuestion() + 1);

        //If over 10 questions disable answer buttons and send scores to next screen

        if (g.getThisQuestion() < g.getMaxQuestions()) {
            tv_Score3.setText(Integer.toString(g.getScore()));
            nextQuestion();
            startCountDown();
        } else {
            btn_Answer9.setEnabled(false);
            btn_Answer10.setEnabled(false);
            btn_Answer11.setEnabled(false);
            btn_Answer12.setEnabled(false);

            tv_Score3.setText("Score: " + g.getScore());

            g.getScore();
            SharedPreferences preferences = getSharedPreferences("PREFS", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("lastScore", g.getScore());
            editor.apply();

        }

    }

    private void nextQuestion() {

        //Create questions

        g.makeNewQuestion();
        int[] answer = g.getCurrentQuestion().getAnswerArray();

        btn_Answer9.setText(Integer.toString(answer[0]));
        btn_Answer10.setText(Integer.toString(answer[1]));
        btn_Answer11.setText(Integer.toString(answer[2]));
        btn_Answer12.setText(Integer.toString(answer[3]));

        btn_Answer9.setEnabled(true);
        btn_Answer10.setEnabled(true);
        btn_Answer11.setEnabled(true);
        btn_Answer12.setEnabled(true);

        tv_Question3.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_MessageBar3.setText(g.getAnswerCorrect() + "/" + (g.getTotalQuestions()));
    }

    //Back to menu button
    private void defineButtons() {
        findViewById(R.id.btn_BackToMenu3).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_BackToMenu3:
                    Intent LevelThree = new Intent(LevelThree.this, MainActivity.class);
                    startActivity(LevelThree);
                    break;
            }
        }
    };

    //Play Correct Sound

    public void playCorrect() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.clap);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer player) {
                    //stopPlayer();
                }
            });
        }
        player.start();
    }

    private void startCountDown() {

        countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_Timer2.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countDownTimer.cancel();
                anotherQuestion();
            }
        }.start();
    }
}