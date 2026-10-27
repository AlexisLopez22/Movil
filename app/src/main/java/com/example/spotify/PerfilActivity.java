package com.example.spotify;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.spotify.R;

public class PerfilActivity extends AppCompatActivity {

    // 1. Declaración de Vistas
    private ImageView imgFotoPerfil;
    private TextView tvNombreUsuario, tvCorreoUsuario, tvVolverHomeP, tvEditarPerfil;
    private EditText etEditarNombre, etEditarCorreo, etEditarPassword;
    private Button btnGuardarPerfil, btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        // 2. Vinculación con los IDs del XML
        imgFotoPerfil = findViewById(R.id.imgFotoPerfil);
        tvNombreUsuario = findViewById(R.id.tvNombreUsuario);
        tvCorreoUsuario = findViewById(R.id.tvCorreoUsuario);
        tvVolverHomeP = findViewById(R.id.tvVolverHomeP);
        tvEditarPerfil = findViewById(R.id.tvEditarPerfil);

        etEditarNombre = findViewById(R.id.etEditarNombre);
        etEditarCorreo = findViewById(R.id.etEditarCorreo);
        etEditarPassword = findViewById(R.id.etEditarPassword);

        btnGuardarPerfil = findViewById(R.id.btnGuardarPerfil);
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        // --- NUEVO CÓDIGO: RECUPERAR Y MOSTRAR DATOS ---
        android.content.SharedPreferences prefs = getSharedPreferences("MisPreferencias", MODE_PRIVATE);
        String nombreGuardado = prefs.getString("nombreUsuario", "Usuario no encontrado");
        String correoGuardado = prefs.getString("correoUsuario", "correo@noencontrado.com");

        tvNombreUsuario.setText(nombreGuardado);
        tvCorreoUsuario.setText(correoGuardado);

        etEditarNombre.setText(nombreGuardado);
        etEditarCorreo.setText(correoGuardado);
        // -----------------------------------------------

        // 3. Documentación de Eventos y Lógica

        // Volver a la pantalla anterior
        tvVolverHomeP.setOnClickListener(v -> finish());

        // Guardar la información actualizada del perfil
        btnGuardarPerfil.setOnClickListener(v -> {
            String nuevoNombre = etEditarNombre.getText().toString().trim();
            String nuevoCorreo = etEditarCorreo.getText().toString().trim();
            String nuevaPassword = etEditarPassword.getText().toString().trim();

            // TODO: Rellenar con la validación de campos y actualización en BD / SharedPreferences
        });

        // Cerrar sesión del usuario
        btnCerrarSesion.setOnClickListener(v -> {
            // TODO: Limpiar la sesión actual y redirigir a LoginActivity
            Intent intent = new Intent(PerfilActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });
    }
}