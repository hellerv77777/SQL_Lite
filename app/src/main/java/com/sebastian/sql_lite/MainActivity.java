package com.sebastian.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sebastian.sql_lite.dataBase.UserListActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button buttonRegister;
    private Button buttonShowlist;
    private Button buttonApi;
    private Button buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.setTitle("Aplicacion CRUD - SQLite");
        bindView();
    }

    private void bindView() {

        buttonRegister = findViewById(R.id.buttonRegister);
        buttonShowlist = findViewById(R.id.buttonShowlist);
        buttonApi = findViewById(R.id.buttonApi);
        buttonAbout = findViewById(R.id.buttonAbout);

        buttonRegister.setOnClickListener(this);
        buttonShowlist.setOnClickListener(this);
        buttonApi.setOnClickListener(this);
        buttonAbout.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        return;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            
            case R.id.buttonRegister:
                startActivity(new Intent(MainActivity.this,RegistryActivity.class));
            break;
            case R.id.buttonShowlist:
                startActivity(new Intent(MainActivity.this, UserListActivity.class));
            break;
            case R.id.buttonApi:
                startActivity(new Intent(MainActivity.this, TemperaturaActivity.class));
                break;

            case R.id.buttonAbout:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
            break;
        }

    }
}