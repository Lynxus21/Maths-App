package com.example.bmcdevapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        defineButtons();
    }

    public void defineButtons() {
        findViewById(R.id.btn_LevelOne).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn_LevelTwo).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn_LevelThree).setOnClickListener(buttonClickListener);
        findViewById(R.id.btn_ViewScores).setOnClickListener(buttonClickListener);


    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_LevelOne:
                    Intent LevelOne = new Intent(MainActivity.this, LevelOne.class);
                    startActivity(LevelOne);
                    break;

                case R.id.btn_LevelTwo:
                    Intent LevelTwo = new Intent(MainActivity.this, LevelTwo.class);
                    startActivity(LevelTwo);
                    break;

                case R.id.btn_LevelThree:
                    Intent LevelThree = new Intent(MainActivity.this, LevelThree.class);
                    startActivity(LevelThree);
                    break;

                case R.id.btn_ViewScores:
                    Intent Scores = new Intent(MainActivity.this, Score.class);
                    startActivity(Scores);
                    break;
            }
        }
    };
}
