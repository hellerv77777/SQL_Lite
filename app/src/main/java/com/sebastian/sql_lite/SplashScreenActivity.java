package com.sebastian.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashScreenActivity extends AppCompatActivity {


    //TIEMPO DE ESPERA PARA PASAR A LA SIGUIENTE ACTIVIDAD
    private static final int WAIT_TIME = 3000;
    //TIEMPO DE PASO HASTA LLEGAR AL WAIT TIME
    private static final int TIME_STEPS = 1000;

    //CONTADOR
    private CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        countDownTimer = new CountDownTimer(WAIT_TIME,TIME_STEPS) {
            @Override
            public void onTick(long millisUntilFinished) {
                //SE EJECUTA UNA ACCION CADA TIME_STEPS
            }

            @Override
            public void onFinish() {

                startActivity(new Intent(SplashScreenActivity.this, LoginActivity.class));
                finish();

            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}