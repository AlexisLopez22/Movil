package com.example.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotify.interfaces.AutenticacionAPI;
import com.example.spotify.modelos.RespuestaLogin;
import com.example.spotify.modelos.SolicitudRegistro;
import com.example.spotify.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    private LinearLayout layoutCamposArtista;
    private Spinner spinnerTipoCuenta;
    private EditText etNombreArtistico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_sesion);

        EditText etNombreR = findViewById(R.id.etNombreR);
        EditText etCorreoR = findViewById(R.id.etCorreoR);
        EditText etPasswordR = findViewById(R.id.etPasswordR);
        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);
        Button btnIrInicioSesion = findViewById(R.id.btnIraInicioSesion);

        // Inicializar vistas nuevas del XML
        layoutCamposArtista = findViewById(R.id.layoutCamposArtista);
        spinnerTipoCuenta = findViewById(R.id.spinnerTipoCuenta);
        etNombreArtistico = findViewById(R.id.etNombreArtistico);

        // Configurar el Spinner
        String[] opciones = {"Selecciona una opción", "Oyente", "Artista"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                opciones
        );
        spinnerTipoCuenta.setAdapter(adapter);

        spinnerTipoCuenta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String seleccion = parent.getItemAtPosition(position).toString();
                if (seleccion.equals("Artista")) {
                    layoutCamposArtista.setVisibility(View.VISIBLE);
                } else {
                    layoutCamposArtista.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnIrInicioSesion.setOnClickListener(v -> finish());

        btnRegistrarse.setOnClickListener(v -> {
            String nombre = etNombreR.getText().toString().trim();
            String correo = etCorreoR.getText().toString().trim();
            String password = etPasswordR.getText().toString();
            String tipoCuenta = spinnerTipoCuenta.getSelectedItem().toString();

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(RegistroActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length() < 8) {
                Toast.makeText(RegistroActivity.this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                return;
            }

            if (tipoCuenta.equals("Selecciona una opción")) {
                Toast.makeText(RegistroActivity.this, "Por favor selecciona un tipo de cuenta", Toast.LENGTH_SHORT).show();
                return;
            }

            if (tipoCuenta.equals("Artista") && etNombreArtistico.getText().toString().trim().isEmpty()) {
                Toast.makeText(RegistroActivity.this, "Ingresa tu nombre artístico", Toast.LENGTH_SHORT).show();
                return;
            }

            // Nota: Si el backend requiere el nombre artístico, debes agregarlo a SolicitudRegistro
            SolicitudRegistro datos = new SolicitudRegistro(nombre, correo, password);

            AutenticacionAPI api = RetrofitClient.INSTANCE.getAutenticacionApi();
            btnRegistrarse.setEnabled(false);

            api.registrarUsuario(datos).enqueue(new Callback<RespuestaLogin>() {
                @Override
                public void onResponse(Call<RespuestaLogin> call, Response<RespuestaLogin> response) {
                    btnRegistrarse.setEnabled(true);

                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(RegistroActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegistroActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.code() == 422) {
                        try {
                            String errorServer = response.errorBody().string();
                            android.util.Log.e("ERROR_LARAVEL", errorServer);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(RegistroActivity.this, "Error de validación 422", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(RegistroActivity.this, "Error al registrar. Código HTTP: " + response.code(), Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<RespuestaLogin> call, Throwable error) {
                    btnRegistrarse.setEnabled(true);
                    Toast.makeText(RegistroActivity.this, "Error de conexión: " + error.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        });
    }
}