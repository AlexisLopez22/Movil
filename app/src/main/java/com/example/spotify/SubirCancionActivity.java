package com.example.spotify;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SubirCancionActivity extends AppCompatActivity {

    ImageButton btnVolver;
    ImageView imgPortadaPreview;
    EditText etTituloCancion, etGenero;
    LinearLayout btnSeleccionarAudio;
    TextView tvNombreArchivo;
    Button btnPublicar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_cancion);

        btnVolver = findViewById(R.id.btnVolver);
        imgPortadaPreview = findViewById(R.id.imgPortadaPreview);
        etTituloCancion = findViewById(R.id.etTituloCancion);
        etGenero = findViewById(R.id.etGenero);
        btnSeleccionarAudio = findViewById(R.id.btnSeleccionarAudio);
        tvNombreArchivo = findViewById(R.id.tvNombreArchivo);
        btnPublicar = findViewById(R.id.btnPublicar);

        btnVolver.setOnClickListener(v -> finish());

        btnSeleccionarAudio.setOnClickListener(v -> {
            Toast.makeText(this, "Selecciona un archivo MP3", Toast.LENGTH_SHORT).show();
        });

        // Validar y publicar
        btnPublicar.setOnClickListener(v -> {
            String titulo = etTituloCancion.getText().toString().trim();
            String genero = etGenero.getText().toString().trim();

            if (titulo.isEmpty() || genero.isEmpty()) {
                Toast.makeText(this, "Completa el título y el género", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Publicando canción...", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}