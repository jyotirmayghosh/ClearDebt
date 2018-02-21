package com.greentech.jyotirmay.cleardebt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("clearDebt", MODE_PRIVATE);
                String valueSaved = preferences.getString("login", "N/A");
                if (valueSaved.equals(CarouselActivity.PREF)) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), CarouselActivity.class);
                    startActivity(intent);
                }
                finish();
            }
        };
        Timer timer=new Timer();
        timer.schedule(timerTask, 2000);
    }
}
