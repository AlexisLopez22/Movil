
package com.example.spotify;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Activity_home extends AppCompatActivity {

    private MediaPlayer reproductor;

    private ImageButton btnPlayPause;
    private ImageButton btnNext;
    private ImageButton btnPrevious;

    private TextView tvCancionActual;
    private TextView tvArtistaActual;

    private int cancionActual = 0;

    private final int[] canciones = {
            R.raw.faint,
            R.raw.human,
            R.raw.te_juro_que_te_amo,
            R.raw.amarte_a_la_antigua,
            R.raw.high_hopes
    };

    private final String[] nombresCanciones = {
            "Faint",
            "Human",
            "Te Juro Que Te Amo",
            "Amarte a la Antigua",
            "High Hopes"
    };

    private final String[] nombresArtistas = {
            "Linkin Park",
            "Artista de Human",
            "Artista de Te Juro Que Te Amo",
            "Artista de Amarte a la Antigua",
            "Panic! At The Disco"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        btnNext = findViewById(R.id.btnSiguiente);
        btnPrevious = findViewById(R.id.btnAnterior);
        tvCancionActual = findViewById(R.id.tvCancionActual);
        tvArtistaActual = findViewById(R.id.tvArtistaActual);
        reproducirCancion();

        // Pausar o reanudar la canción
        btnPlayPause.setOnClickListener(v -> {
            if (reproductor != null) {
                if (reproductor.isPlaying()) {
                    reproductor.pause();

                    btnPlayPause.setImageResource(
                            android.R.drawable.ic_media_play
                    );
                } else {
                    reproductor.start();
                    btnPlayPause.setImageResource(
                            android.R.drawable.ic_media_pause
                    );
                }
            }
        });
        // Pasar a la siguiente canción
        btnNext.setOnClickListener(v -> {
            cancionActual++;
            if (cancionActual >= canciones.length) {
                cancionActual = 0;
            }
            reproducirCancion();
        });
        // Regresar a la canción anterior
        btnPrevious.setOnClickListener(v -> {
            cancionActual--;
            if (cancionActual < 0) {
                cancionActual = canciones.length - 1;
            }
            reproducirCancion();
        });
    }

    private void reproducirCancion() {
        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
        reproductor = MediaPlayer.create(
                this,
                canciones[cancionActual]
        );
        if (reproductor != null) {
            // Actualizar el nombre de la canción y del artista
            tvCancionActual.setText(nombresCanciones[cancionActual]);
            tvArtistaActual.setText(nombresArtistas[cancionActual]);
            reproductor.start();
            btnPlayPause.setImageResource(
                    android.R.drawable.ic_media_pause
            );
            reproductor.setOnCompletionListener(mp -> {
                cancionActual++;
                if (cancionActual >= canciones.length) {
                    cancionActual = 0;
                }
                reproducirCancion();
            });
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (reproductor != null) {
            reproductor.release();
            reproductor = null;
        }
    }
}
