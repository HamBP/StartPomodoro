package org.algosketch.StartPomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {
    TextView stateTextView;
    TextView currentTime;
    TextView currentRepetition;
    Button finishBtn;

    Uri notification;
    Ringtone ringtone;

    int m, s;
    int cnt;
    int currentCnt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        stateTextView = findViewById(R.id.state);
        currentTime = findViewById(R.id.current_time);
        currentRepetition = findViewById(R.id.current_repetition);
        finishBtn = findViewById(R.id.btn_finish);

        finishBtn.setOnClickListener(new FinishBtn());

        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ringtone = RingtoneManager.getRingtone(getApplicationContext(), notification);

        final int cycleLength = getIntent().getIntExtra("cycleLength", 30);
        cnt = getIntent().getIntExtra("repetition", 1);

        initActivity(cycleLength);

        m = (cycleLength/30) * 25;
        s = 0;
        currentCnt = 1;

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // 타이머가 0
                        if(m == 0 & s == 1) {
                            ringtone.play();
                            // 집중
                            if(stateTextView.getText().toString().equals("집중")) {
                                if(currentCnt == cnt) {
                                    finish();
                                }
                                else {
                                    stateTextView.setText(R.string.state_break);
                                    m = (cycleLength / 30) * 5;
                                    s = 0;
                                }
                            }
                            else { // 휴식
                                ++currentCnt;
                                m = (cycleLength / 30) *25;
                                s = 0;
                            }
                        }

                        if(s > 0) --s;
                        else {
                            s = 59;
                            --m;
                            --cnt;
                        }

                        currentTime.setText(m + ":" + s);
                    }
                });
            }
        };

        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    void initActivity(int cycleLength) {
        currentRepetition.setText("1회/" + cnt + "회");

        if(cycleLength == 30) currentTime.setText("25:00");
        else currentTime.setText("50:00");
    }

    class FinishBtn implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            finish();
        }
    }
}

