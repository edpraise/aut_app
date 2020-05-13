package com.example.manahomectrl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class WaterSprinkler extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 600000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private  Button mButtonReset;
//    private Switch switchButton;

    private CountDownTimer mCountDownTimer;

    private boolean mTimeRunning;

    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    SharedPreferences.Editor prefEditor;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefEditor = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).edit();
        prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
//        switchButton = findViewById(R.id.switch1);
        mTextViewCountDown = findViewById(R.id.textView3);
        mButtonStartPause = findViewById(R.id.button7);
        mButtonReset = findViewById(R.id.button8);

//        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                if (isChecked){
//                    Toast.makeText(WaterSprinkler.this, "enabled", Toast.LENGTH_SHORT).show();
//                    prefEditor.putString("checked","yes");
//                    prefEditor.apply();
//                }else {
//                    Toast.makeText(WaterSprinkler.this,"disabled",Toast.LENGTH_SHORT).show();
//                    prefEditor.putString("check", "false");
//                    prefEditor.apply();
//                }
//
//            }
//        });
//        if (prefs.getString("checked","no").equals("yes")){
//            switchButton.setChecked(true);
//        }else {
//            switchButton.setChecked(false);
//        }

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mTimeRunning){
                    pauseTimer();
                }else {
                    startTimer();
                }

            }
        });
        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();

            }
        });
        updateCountDownText();

    }
    private  void  startTimer(){
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntillFinished) {
                mTimeLeftInMillis = millisUntillFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish() {
                mTimeRunning = false;
                mButtonStartPause.setText("start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();
        mTimeRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer(){
        mCountDownTimer.cancel();
        mTimeRunning=false;
        mButtonStartPause.setText("start");
        mButtonReset.setVisibility(View.VISIBLE);

    }
    private void resetTimer(){
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);

    }

    private void  updateCountDownText(){
        int minutes =(int) (mTimeLeftInMillis / 1000) /60;
        int seconds =(int) (mTimeLeftInMillis / 1000) %60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes,seconds);
        mTextViewCountDown.setText(timeLeftFormatted);

    }
    }



