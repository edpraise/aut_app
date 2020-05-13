package com.example.manahomectrl;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LeakageActivity extends AppCompatActivity {
    Button nbutton;
    EditText edtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leakage);

        nbutton = findViewById(R.id.btnset);
        edtime = findViewById(R.id.editText3);

        nbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int time = Integer.parseInt(edtime.getText().toString());
                Intent i = new Intent(LeakageActivity.this, Alarm.class);

                PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(),0, i, 0);
                AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+time * 1000,pi );

            }
        });
    }
}
