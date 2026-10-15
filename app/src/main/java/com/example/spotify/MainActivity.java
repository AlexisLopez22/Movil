package com.example.spotify;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotify.interfaces.AutenticacionAPI;
import com.example.spotify.modelos.RespuestaLogin;
import com.example.spotify.modelos.SolicitudLogin;
import com.example.spotify.network.ClienteRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText etCorreo, etPassword;
    Button btnIniciarSesion, btnRegistro;
    Button btnPlayPause , btnNext ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio_sesion);

        etCorreo = findViewById(R.id.etCorreo);
        etPassword = findViewById(R.id.etPassword);
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(v -> {
            Intent intent = new Intent(
                    MainActivity.this,
                    RegistroActivity.class
            );
            startActivity(intent);
        });
        btnIniciarSesion.setOnClickListener(v -> iniciarSesion());
    }
    private void iniciarSesion() {
        String correo = etCorreo.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (correo.isEmpty() || password.isEmpty()) {
            Toast.makeText(
                    this,
                    "Completa correo y contraseña",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }
        SolicitudLogin datos = new SolicitudLogin(correo, password);
        AutenticacionAPI api = ClienteRetrofit.obtenerAPI();
        api.iniciarSesion(datos).enqueue(new Callback<RespuestaLogin>() {

            @Override
            public void onResponse(
                    Call<RespuestaLogin> call,
                    Response<RespuestaLogin> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RespuestaLogin respuesta = response.body();
                    Toast.makeText(
                            MainActivity.this,
                            "Bienvenido " + respuesta.getUser().getName(),
                            Toast.LENGTH_SHORT
                    ).show();
                    // Abrir Home después del inicio de sesión exitoso
                    Intent intent = new Intent(
                            MainActivity.this,
                            Activity_home.class
                    );
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(
                            MainActivity.this,
                            "Correo o contraseña incorrectos",
                            Toast.LENGTH_SHORT
                    ).show();
                }
            }
            @Override
            public void onFailure(
                    Call<RespuestaLogin> call,
                    Throwable error) {

                Toast.makeText(
                        MainActivity.this,
                        "Error de conexión",
                        Toast.LENGTH_LONG
                ).show();
            }
        });
    }
}
