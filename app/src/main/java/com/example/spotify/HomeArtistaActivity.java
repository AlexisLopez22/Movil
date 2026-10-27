package com.example.spotify;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class HomeArtistaActivity extends AppCompatActivity {

    ImageButton btnBuscar, btnInicio, btnPlaylist, btnCrearCancion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_artista);

        btnBuscar = findViewById(R.id.btnBuscar);
        btnInicio = findViewById(R.id.btnInicio);
        btnPlaylist = findViewById(R.id.btnPlaylist);
        btnCrearCancion = findViewById(R.id.btnCrearCancion);

        btnCrearCancion.setOnClickListener(v -> {
            Intent intent = new Intent(HomeArtistaActivity.this, SubirCancionActivity.class);
            startActivity(intent);
        });

        btnPlaylist.setOnClickListener(v -> {
            Intent intent = new Intent(HomeArtistaActivity.this, PlaylistActivity.class);
            startActivity(intent);
        });
    }
}