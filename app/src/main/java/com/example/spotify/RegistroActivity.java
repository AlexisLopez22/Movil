package com.example.spotify;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
            String password = etPasswordR.getText().toString().trim();

            if (nombre.isEmpty() || correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                        RegistroActivity.this,
                        "Completa todos los campos",
                        Toast.LENGTH_SHORT
                ).show();
                return;
            }

            Toast.makeText(
                    RegistroActivity.this,
                    "Registrado Correctamente",
                    Toast.LENGTH_SHORT
            ).show();
        });
    }
}