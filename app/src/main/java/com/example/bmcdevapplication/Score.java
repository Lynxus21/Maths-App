package com.example.bmcdevapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Score extends AppCompatActivity {

    TextView tv_score;

    int lastScore;
    int best1, best2, best3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        defineButtons();

        tv_score = (TextView) findViewById(R.id.tv_score);

        SharedPreferences preferences = getSharedPreferences("PREFS", 0);
        lastScore = preferences.getInt("lastScore", 0);
        best1 = preferences.getInt("best1", 0);
        best2 = preferences.getInt("best2", 0);
        best3 = preferences.getInt("best3", 0);

        //Replace if there is a higher score

        if (lastScore > best3) {
            best3 = lastScore;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best3", best3);
            editor.apply();
        }
        if (lastScore > best2) {
            int temp = best2;
            best2 = lastScore;
            best3 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best3", best3);
            editor.apply();
        }
        if (lastScore > best1) {
            int temp = best1;
            best1 = lastScore;
            best2 = temp;
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("best2", best2);
            editor.putInt("best1", best1);
            editor.apply();
        }

        tv_score.setText("LAST SCORE: " + lastScore + "\n" +
                "Best1: " + best1 + "\n" +
                "Best2: " + best2 + "\n" +
                "Best3: " + best3);
    }

    //Back to menu button
    private void defineButtons() {
        findViewById(R.id.btn_BackToMenu4).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_BackToMenu4:
                    Intent Score = new Intent(Score.this, MainActivity.class);
                    startActivity(Score);
                    break;
            }
        }
    };
}