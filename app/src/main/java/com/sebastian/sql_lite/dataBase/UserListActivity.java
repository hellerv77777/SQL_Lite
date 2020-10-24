package com.sebastian.sql_lite.dataBase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import com.sebastian.sql_lite.MainActivity;
import com.sebastian.sql_lite.MyUserRecyclerViewAdapter;
import com.sebastian.sql_lite.R;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsers;
    private EditText editTextBuscar;
    private MyUserRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Aplicacion CRUD - SQLite");
        bindView();

        adapter = new MyUserRecyclerViewAdapter(UserQuery.getListUser(this),this);

        if(UserQuery.getListUser(UserListActivity.this).size()==0){

            Crouton.makeText(UserListActivity.this, "No hay datos para mostrar", Style.ALERT).show();
        }

        recyclerViewUsers.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerViewUsers.setAdapter(adapter);

        editTextBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence caracter, int start, int before, int count) {


                if(UserQuery.getListUserByCharacter(UserListActivity.this,caracter.toString()).size()==0){
                    Crouton.makeText(UserListActivity.this, "No hay datos para mostrar", Style.ALERT).show();

                }
                adapter.updateListUser(UserQuery.getListUserByCharacter(UserListActivity.this,caracter.toString()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void bindView() {

        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);
        editTextBuscar = findViewById(R.id.editTextBuscar);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        startActivity(new Intent(UserListActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}