package com.sebastian.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextUser;
    private EditText editTextPass;
    private Button buttonLogin;


    //lOGIN DATA

    private static final String USER_LOGIN = "user";
    private static final String USER_PASS = "123";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.setTitle("Aplicacion CRUD - SQLite");
        bindView();


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextUser.getText().toString().isEmpty()){
                    editTextUser.setError(getString(R.string.error_user));
                    editTextUser.requestFocus();

                    Crouton.makeText(LoginActivity.this, "Ingresa tu nombre de usuario", Style.ALERT).show();
                    return;
                }

                if(editTextPass.getText().toString().isEmpty()){
                    editTextPass.setError(getString(R.string.error_pass));
                    editTextPass.requestFocus();

                    Crouton.makeText(LoginActivity.this, "Ingresa tu clave de acceso", Style.ALERT).show();

                    return;
                }

                validateLogin(editTextUser.getText().toString(),editTextPass.getText().toString());
            }
        });
    }

    private void validateLogin(String user, String pass) {

        if(user.equals(USER_LOGIN) && pass.equals(USER_PASS)){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }else{
            editTextPass.requestFocus();
            editTextUser.requestFocus();
            Crouton.makeText(LoginActivity.this, "Usuario o clave invalida", Style.ALERT).show();
        }
    }

    private void bindView() {

        editTextUser = findViewById(R.id.editTextUser);
        editTextPass = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    @Override
    public void onBackPressed() {
        return;
    }
}