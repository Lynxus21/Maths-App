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

public class LevelTwo extends AppCompatActivity {

    Button btn_Start2;
    Button btn_Answer5;
    Button btn_Answer6;
    Button btn_Answer7;
    Button btn_Answer8;
    TextView tv_Question2;
    TextView tv_MessageBar2;
    TextView tv_Score2;
    TextView tv_Timer1;

    MediaPlayer player;

    Game g;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_two);
        defineButtons();

        btn_Start2 = findViewById(R.id.btn_Start2);
        btn_Answer5 = findViewById(R.id.btn_Answer5);
        btn_Answer6 = findViewById(R.id.btn_Answer6);
        btn_Answer7 = findViewById(R.id.btn_Answer7);
        btn_Answer8 = findViewById(R.id.btn_Answer8);

        tv_Score2 = findViewById(R.id.tv_Score2);
        tv_MessageBar2 = findViewById(R.id.tv_MessageBar2);
        tv_Question2 = findViewById(R.id.tv_Question2);
        tv_Timer1 = findViewById(R.id.tv_Timer1);

        tv_Question2.setText("");
        tv_MessageBar2.setText("Press start game to play");
        tv_Score2.setText("0 Points");
        tv_Timer1.setText("  seconds remaining");

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

        btn_Start2.setOnClickListener(startButtonClickListener);

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {

            //Displays total amount of questions
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                if (g.checkAnswer(answerSelected)) {
                    countDownTimer.cancel();
                    anotherQuestion();
                    playCorrect();
                } else {
                    countDownTimer.cancel();
                    anotherQuestion();
                }
            }
        };

        btn_Answer5.setOnClickListener(answerButtonClickListener);
        btn_Answer6.setOnClickListener(answerButtonClickListener);
        btn_Answer7.setOnClickListener(answerButtonClickListener);
        btn_Answer8.setOnClickListener(answerButtonClickListener);
    }

    private void anotherQuestion() {
        tv_Score2.setText(Integer.toString(g.getScore()));
        g.setThisQuestion(g.getThisQuestion() + 1);

        //If over 10 questions disable answer buttons and send scores to next screen

        if (g.getThisQuestion() < g.getMaxQuestions()) {
            tv_Score2.setText(Integer.toString(g.getScore()));
            startCountDown();
            nextQuestion();
        } else {
            btn_Answer5.setEnabled(false);
            btn_Answer6.setEnabled(false);
            btn_Answer7.setEnabled(false);
            btn_Answer8.setEnabled(false);

            tv_Score2.setText("Score: " + g.getScore());

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

        btn_Answer5.setText(Integer.toString(answer[0]));
        btn_Answer6.setText(Integer.toString(answer[1]));
        btn_Answer7.setText(Integer.toString(answer[2]));
        btn_Answer8.setText(Integer.toString(answer[3]));

        btn_Answer5.setEnabled(true);
        btn_Answer6.setEnabled(true);
        btn_Answer7.setEnabled(true);
        btn_Answer8.setEnabled(true);

        tv_Question2.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_MessageBar2.setText(g.getAnswerCorrect() + "/" + (g.getTotalQuestions()));
    }

    //Back to menu button
    private void defineButtons() {
        findViewById(R.id.btn_BackToMenu2).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_BackToMenu2:
                    Intent LevelTwo = new Intent(LevelTwo.this, MainActivity.class);
                    startActivity(LevelTwo);
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

        countDownTimer = new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {
                tv_Timer1.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                countDownTimer.cancel();
                anotherQuestion();
            }
        }.start();
    }
}