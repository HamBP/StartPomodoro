package org.algosketch.StartPomodoro;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView halfHourOption;
    TextView oneHourOption;
    Button cntUpBtn;
    Button cntDownBtn;
    TextView repetitionCnt;
    TextView totalTimeTextView;
    Button startBtn;
    int cnt = 1;
    int cycleTime = 30;         // 단위 : 분
    int totalTimeMinute = 30;   // 단위 : 분
    TotalTime totalTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        halfHourOption = findViewById(R.id.half_hour_option);
        oneHourOption = findViewById(R.id.one_hour_option);
        cntUpBtn = findViewById(R.id.btn_repetition_up);
        cntDownBtn = findViewById(R.id.btn_repetition_down);
        repetitionCnt = findViewById(R.id.repetition_cnt);
        totalTimeTextView =findViewById(R.id.total_time);
        startBtn = findViewById(R.id.btn_start);
        totalTime = new TotalTime();

        halfHourOption.setOnClickListener(new OptionOnClick());
        oneHourOption.setOnClickListener(new OptionOnClick());
        cntUpBtn.setOnClickListener(new RepetitionOnClick());
        cntDownBtn.setOnClickListener(new RepetitionOnClick());
        startBtn.setOnClickListener(new StartButtonOnClick());
    }

    class OptionOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == halfHourOption) {
                halfHourOption.setTypeface(null, Typeface.BOLD);
                oneHourOption.setTypeface(null, Typeface.NORMAL);
                cycleTime = 30;
                totalTimeMinute = cycleTime * cnt;
            }
            if(view == oneHourOption) {
                oneHourOption.setTypeface(null, Typeface.BOLD);
                halfHourOption.setTypeface(null, Typeface.NORMAL);
                cycleTime = 60;
                totalTimeMinute = cycleTime * cnt;
            }

            setTotalTimeTextView(totalTimeMinute);
        }
    }

    class RepetitionOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if(view == cntUpBtn) {
                ++cnt;
            }
            if(view == cntDownBtn) {
                if(cnt > 1) --cnt;
                else showToast("반복 횟수가 1보다 작을 수 없습니다.");
            }
            repetitionCnt.setText(Integer.toString(cnt));

            totalTimeMinute = cycleTime * cnt;
            setTotalTimeTextView(totalTimeMinute);
        }
    }

    class StartButtonOnClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // TODO : new intent
        }
    }

    private void setTotalTimeTextView(int minute) {
        StringBuilder totalTimeString = new StringBuilder(20);
        totalTime.minuteTohours(minute);
        if(totalTime.getHour() > 0) {
            totalTimeString.append(totalTime.getHour() + "시간 ");
        }
        totalTimeString.append(totalTime.getMinute() + "분");
        totalTimeTextView.setText(totalTimeString.toString());
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}