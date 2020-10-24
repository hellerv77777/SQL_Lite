package com.sebastian.sql_lite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.sebastian.sql_lite.Retrofit.Services;
import com.sebastian.sql_lite.Retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TemperaturaActivity extends AppCompatActivity {

    private Date fecha = new Date();
    private TextView textFecha;
    private TextView textTemperatura;
    private TextView textHumedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperatura);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle("Aplicacion CRUD - SQLite");

        bindView();

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat dateFormat2 = new SimpleDateFormat("HH:mm:ss");

        textFecha.setText(new StringBuilder().append(dateFormat.format(fecha)).append(" - ").append(dateFormat2.format(fecha)));

        RetrofitClient.getDataApi("https://www.pnk.cl").create(Services.class).getDataApi("https://www.pnk.cl/muestra_datos.php").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {


                try {

                    JSONObject jsonObject = new JSONObject(response.body());
                    String temperatura = jsonObject.getString("temperatura");
                    String humedad = jsonObject.getString("humedad");
                    textTemperatura.setText(new StringBuilder().append(temperatura).append(" Cº"));
                    textHumedad.setText(new StringBuilder().append(humedad).append(" %"));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

                Crouton.makeText(TemperaturaActivity.this, "Se requiere conexion a internet para visulizar los datos", Style.ALERT).show();
            }
        });
    }

    private void bindView() {

        textFecha = findViewById(R.id.textFecha);
        textTemperatura = findViewById(R.id.textTemperatura);
        textHumedad = findViewById(R.id.textHumedad);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(TemperaturaActivity.this, MainActivity.class));
        finish();
    }
}