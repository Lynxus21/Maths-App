package com.example.bmcdevapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelOne extends AppCompatActivity {

    Button btn_Start1;
    Button btn_Answer1;
    Button btn_Answer2;
    Button btn_Answer3;
    Button btn_Answer4;
    TextView tv_Question1;
    TextView tv_MessageBar1;
    TextView tv_Score1;

    MediaPlayer player;

    Game g;

    //int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_one);
        defineButtons();

        btn_Start1 = findViewById(R.id.btn_Start1);
        btn_Answer1 = findViewById(R.id.btn_Answer1);
        btn_Answer2 = findViewById(R.id.btn_Answer2);
        btn_Answer3 = findViewById(R.id.btn_Answer3);
        btn_Answer4 = findViewById(R.id.btn_Answer4);

        tv_Score1 = findViewById(R.id.tv_Score1);
        tv_MessageBar1 = findViewById(R.id.tv_MessageBar1);
        tv_Question1 = findViewById(R.id.tv_Question1);

        tv_Question1.setText("");
        tv_MessageBar1.setText("Press start game to play");
        tv_Score1.setText(" Points");

        //If clicked start button goes invisible and game begins
        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                g = new Game();
                g.setThisQuestion(0);
                nextQuestion();
            }
        };

        btn_Start1.setOnClickListener(startButtonClickListener);

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {

            //Adds question to total questions
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                g.setThisQuestion(g.getThisQuestion() + 1);
                //Checks answer and play sounds then go to next question. If over 10 questions disable buttons, end game and save scores.
                if (g.checkAnswer(answerSelected)) {
                    playCorrect();
                }
                tv_Score1.setText(Integer.toString(g.getScore()));
                if (g.getThisQuestion() < g.getMaxQuestions()) {
                    nextQuestion();
                } else {
                    btn_Answer1.setEnabled(false);
                    btn_Answer2.setEnabled(false);
                    btn_Answer3.setEnabled(false);
                    btn_Answer4.setEnabled(false);

                    tv_Score1.setText("Score: " + Integer.toString(g.getScore()));

                    tv_Score1.setText("Score: " + g.getScore());

                    //Save Score

                    g.getScore();
                    tv_Score1.setText("Score: " + g.getScore());
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("lastScore", g.getScore());
                    editor.apply();

                }
            }
        };

        btn_Answer1.setOnClickListener(answerButtonClickListener);
        btn_Answer2.setOnClickListener(answerButtonClickListener);
        btn_Answer3.setOnClickListener(answerButtonClickListener);
        btn_Answer4.setOnClickListener(answerButtonClickListener);
    }

    private void nextQuestion() {

        //Create questions

        g.makeNewQuestion();
        int[] answer = g.getCurrentQuestion().getAnswerArray();

        btn_Answer1.setText(Integer.toString(answer[0]));
        btn_Answer2.setText(Integer.toString(answer[1]));
        btn_Answer3.setText(Integer.toString(answer[2]));
        btn_Answer4.setText(Integer.toString(answer[3]));

        btn_Answer1.setEnabled(true);
        btn_Answer2.setEnabled(true);
        btn_Answer3.setEnabled(true);
        btn_Answer4.setEnabled(true);

        tv_Question1.setText(g.getCurrentQuestion().getQuestionPhrase());

        tv_MessageBar1.setText(g.getAnswerCorrect() + "/" + (g.getTotalQuestions()));
    }

    //Back to menu button
    private void defineButtons() {
        findViewById(R.id.btn_BackToMenu1).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_BackToMenu1:
                    Intent LevelOne = new Intent(LevelOne.this, MainActivity.class);
                    startActivity(LevelOne);
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
}