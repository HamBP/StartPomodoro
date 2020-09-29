package org.algosketch.StartPomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TimerActivity extends AppCompatActivity {
    int cycleLength;
    int cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        cycleLength = getIntent().getIntExtra("cycleLength", 30);
        cnt = getIntent().getIntExtra("repetition", 1);

        System.out.println("log : " + cycleLength + " " + cnt);
    }
}