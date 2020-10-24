package com.sebastian.sql_lite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sebastian.sql_lite.dataBase.User;
import com.sebastian.sql_lite.dataBase.UserListActivity;
import com.sebastian.sql_lite.dataBase.UserQuery;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class EditUserctivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextLastName;
    private EditText editTextEmail;
    private EditText editTextPass;
    private EditText editTextPass2;
    private Button buttonEliminar;
    private Button buttonModificar;
    private User user;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_userctivity);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindView();

        Bundle bundleIntent = getIntent().getExtras();
        idUser = bundleIntent.getString("id");

        user = UserQuery.getDataUserById(this,idUser);

        if(user!=null){
            editTextName.setText(user.getName());
            editTextLastName.setText(user.getLastName());
            editTextEmail.setText(user.getEmail());
            editTextPass.setText(user.getPass());
            editTextPass2.setText(user.getPass());
        }

        buttonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserQuery.delete(EditUserctivity.this,idUser);
                startActivity(new Intent(EditUserctivity.this, UserListActivity.class));
                finish();
            }
        });

        buttonModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validateInputs()){
                    UserQuery.updateUser(EditUserctivity.this,
                            editTextName.getText().toString(),
                            editTextLastName.getText().toString(),
                            editTextEmail.getText().toString(),
                            editTextPass.getText().toString(),idUser
                    );

                    startActivity(new Intent(EditUserctivity.this, UserListActivity.class));
                    finish();
                }
            }
        });

    }

    private void bindView() {

        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPass = findViewById(R.id.editTextPass);
        editTextPass2 = findViewById(R.id.editTextPass2);
        buttonEliminar=findViewById(R.id.buttonEliminar);
        buttonModificar=findViewById(R.id.buttonModificar);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditUserctivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private boolean validateInputs() {

        if(editTextName.getText().toString().isEmpty()){
            editTextName.setError("Nombre");
            editTextName.requestFocus();
            Crouton.makeText(EditUserctivity.this, "Ingresa un nombre", Style.ALERT).show();
            return false;
        }

        if(editTextLastName.getText().toString().isEmpty()){
            editTextLastName.setError("Apellido");
            editTextLastName.requestFocus();
            Crouton.makeText(EditUserctivity.this, "Ingresa un apellido", Style.ALERT).show();
            return false;
        }

        if(editTextEmail.getText().toString().isEmpty()){
            editTextEmail.setError("Email");
            editTextEmail.requestFocus();
            Crouton.makeText(EditUserctivity.this, "Ingresa un email", Style.ALERT).show();
            return false;
        }

        if(editTextPass.getText().toString().isEmpty()){
            editTextPass.setError("Clave");
            editTextPass.requestFocus();
            Crouton.makeText(EditUserctivity.this, "Ingresa clave", Style.ALERT).show();
            return false;
        }

        if(!editTextPass.getText().toString().equals(editTextPass2.getText().toString())){
            editTextPass.setError("Clave");
            editTextPass.requestFocus();
            Crouton.makeText(EditUserctivity.this, "Las contraseñas no coinciden", Style.ALERT).show();
            return false;
        }

        return true;
    }
}