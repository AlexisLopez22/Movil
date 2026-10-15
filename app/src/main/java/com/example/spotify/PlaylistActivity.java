package com.example.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class PlaylistActivity extends AppCompatActivity {

    // 1. Declaración de Vistas
    private TextView tvVolverHome, tvTituloTop, tvNombrePlaylist, tvDescripcionPlaylist, tvTotalCanciones;
    private ImageView imgPortadaPlaylist;
    private Button btnReproducirPlaylist, btnAgregarCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        // 2. Vinculación con los IDs del XML
        tvVolverHome = findViewById(R.id.tvVolverHome);
        tvTituloTop = findViewById(R.id.tvTituloTop);
        tvNombrePlaylist = findViewById(R.id.tvNombrePlaylist);
        tvDescripcionPlaylist = findViewById(R.id.tvDescripcionPlaylist);
        tvTotalCanciones = findViewById(R.id.tvTotalCanciones);

        imgPortadaPlaylist = findViewById(R.id.imgPortadaPlaylist);
        btnReproducirPlaylist = findViewById(R.id.btnReproducirPlaylist);
        btnAgregarCancion = findViewById(R.id.btnAgregarCancion);

        // 3. Documentación de Eventos y Lógica

        // Volver al Home
        tvVolverHome.setOnClickListener(v -> finish());

        // Iniciar reproducción de la lista completa
        btnReproducirPlaylist.setOnClickListener(v -> {
            // TODO: Pasar la lista de canciones al servicio de reproducción
        });

        // Agregar una nueva canción a la lista
        btnAgregarCancion.setOnClickListener(v -> {
            // TODO: Desplegar selector de canciones para añadir a esta playlist
        });
    }

    // TODO: Método para cargar y mostrar la información vacía o recuperar de la BD
    private void cargarDatosPlaylist() {
        // tvNombrePlaylist.setText(...);
        // tvTotalCanciones.setText(...);
    }
}