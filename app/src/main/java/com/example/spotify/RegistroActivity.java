
package com.example.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotify.interfaces.AutenticacionAPI;
import com.example.spotify.modelos.RespuestaLogin;
import com.example.spotify.modelos.SolicitudRegistro;
import com.example.spotify.network.ClienteRetrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_sesion);

        EditText etNombreR = findViewById(R.id.etNombreR);
        EditText etCorreoR = findViewById(R.id.etCorreoR);
        EditText etPasswordR = findViewById(R.id.etPasswordR);
        Button btnRegistrarse = findViewById(R.id.btnRegistrarse);

        btnRegistrarse.setOnClickListener(v -> {

            String nombre = etNombreR.getText().toString().trim();
            String correo = etCorreoR.getText().toString().trim();
            String password = etPasswordR.getText().toString();

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                        RegistroActivity.this,
                        "Completa todos los campos",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            if (password.length() < 8) {
                Toast.makeText(
                        RegistroActivity.this,
                        "La contraseña debe tener al menos 8 caracteres",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            SolicitudRegistro datos =
                    new SolicitudRegistro(nombre, correo, password);

            AutenticacionAPI api = ClienteRetrofit.obtenerAPI();

            btnRegistrarse.setEnabled(false);

            api.registrarUsuario(datos).enqueue(
                    new Callback<RespuestaLogin>() {

                        @Override
                        public void onResponse(
                                Call<RespuestaLogin> call,
                                Response<RespuestaLogin> response) {

                            btnRegistrarse.setEnabled(true);

                            if (response.isSuccessful()
                                    && response.body() != null) {

                                Toast.makeText(
                                        RegistroActivity.this,
                                        response.body().getMessage(),
                                        Toast.LENGTH_LONG
                                ).show();

                                Intent intent = new Intent(
                                        RegistroActivity.this,
                                        MainActivity.class
                                );

                                startActivity(intent);
                                finish();

                            } else if (response.code() == 422) {

                                Toast.makeText(
                                        RegistroActivity.this,
                                        "Revisa los datos. El correo puede estar registrado "
                                                + "o algún campo ser inválido.",
                                        Toast.LENGTH_LONG
                                ).show();

                            } else {

                                Toast.makeText(
                                        RegistroActivity.this,
                                        "Error al registrar. Código HTTP: "
                                                + response.code(),
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        }

                        @Override
                        public void onFailure(
                                Call<RespuestaLogin> call,
                                Throwable error) {

                            btnRegistrarse.setEnabled(true);

                            Toast.makeText(
                                    RegistroActivity.this,
                                    "Error de conexión: " + error.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    });
        });
    }
}
