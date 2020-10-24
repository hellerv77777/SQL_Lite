package com.sebastian.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sebastian.sql_lite.dataBase.UserListActivity;
import com.sebastian.sql_lite.dataBase.UserQuery;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class RegistryActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPass;
    private EditText editTextPass2;
    private Button buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Aplicacion CRUD - SQLite");
        bindView();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInputs()){

                   UserQuery.insertUser(RegistryActivity.this,
                            editTextName.getText().toString(),
                            editTextLastName.getText().toString(),
                            editTextEmail.getText().toString(),
                            editTextPass.getText().toString()
                   );


                   Crouton.makeText(RegistryActivity.this, "Se ha registrado el usuario correctamente", Style.CONFIRM).show();

                   editTextName.setText("");
                   editTextLastName.setText("");
                   editTextEmail.setText("");
                   editTextPass.setText("");
                   editTextPass2.setText("");

                   editTextName.clearFocus();
                   editTextLastName.clearFocus();
                   editTextEmail .clearFocus();
                   editTextPass.clearFocus();
                   editTextPass2.clearFocus();

                }
            }
        });
    }

    private boolean validateInputs() {

        if(editTextName.getText().toString().isEmpty()){
            editTextName.setError("Nombre");
            editTextName.requestFocus();
            Crouton.makeText(RegistryActivity.this, "Ingresa un nombre", Style.ALERT).show();
            return false;
        }

        if(editTextLastName.getText().toString().isEmpty()){
            editTextLastName.setError("Apellido");
            editTextLastName.requestFocus();
            Crouton.makeText(RegistryActivity.this, "Ingresa un apellido", Style.ALERT).show();
            return false;
        }

        if(editTextEmail.getText().toString().isEmpty()){
            editTextEmail.setError("Email");
            editTextEmail.requestFocus();
            Crouton.makeText(RegistryActivity.this, "Ingresa un email", Style.ALERT).show();
            return false;
        }

        if(editTextPass.getText().toString().isEmpty()){
            editTextPass.setError("Clave");
            editTextPass.requestFocus();
            Crouton.makeText(RegistryActivity.this, "Ingresa clave", Style.ALERT).show();
            return false;
        }

        if(!editTextPass.getText().toString().equals(editTextPass2.getText().toString())){
            editTextPass.setError("Clave");
            editTextPass.requestFocus();
            Crouton.makeText(RegistryActivity.this, "Las contraseñas no coinciden", Style.ALERT).show();
            return false;
        }

        return true;
    }

    private void bindView() {

        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPass);
        editTextPass2 = findViewById(R.id.editTextPass2);
        buttonRegister=findViewById(R.id.buttonRegister);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RegistryActivity.this, UserListActivity.class));
        finish();
    }
}