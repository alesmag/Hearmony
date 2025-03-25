package it.patc.hearmony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.imageview.ShapeableImageView;

import it.patc.hearmony.classes.Data;
import it.patc.hearmony.classes.Playlist;
import it.patc.hearmony.classes.Utente;

public class PlaylistActivity extends AppCompatActivity {

    /* Definizione Attributi */
    ImageView audio1, copertina;
    MediaPlayer player;
    boolean playpausa = false;
    ImageButton backarrow;
    Utente utente;
    TextView h1, descrizione;
    Playlist pl;
    ShapeableImageView other_profile_img;
    TextView other_profile_name;

    /* Apertura della schermata */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        /* Recupero l'utente e la playlist */
        utente = (Utente) getIntent().getSerializableExtra("user");
        pl = (Playlist) getIntent().getSerializableExtra("playlist");
        other_profile_img = findViewById(R.id.other_profile_img);
        other_profile_name = findViewById(R.id.other_profile_name);

        /* Gestisco le view */
        audio1 = findViewById(R.id.audio1);
        backarrow = findViewById(R.id.back_arrow_home);
        h1 = findViewById(R.id.H1);
        copertina = findViewById(R.id.copertina);
        descrizione = findViewById(R.id.descrizione);

        /* Cambio H1 con il nome della playlist */
        h1.setText(pl.getNome());

        /* Cambio la copertina */
        copertina.setImageResource(pl.getImmagine());

        /* Cambio la descrizione */
        descrizione.setText(pl.getDescrizione());

        /* Gestisco il pulsante di audio */
        gestisciPulsantiAudio(audio1, R.raw.prova1);

        /* Gestisco il pulsante per tornare indietro */
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Faccio tornare l'utente alla home */
                if (getIntent().getSerializableExtra("searching") != null) {
                    Intent dati = new Intent(PlaylistActivity.this, SearchActivity.class);
                    dati.putExtra("user", utente);
                    finish();
                    startActivity(dati);
                } else {
                    Intent dati = new Intent(PlaylistActivity.this, HomeActivity.class);
                    dati.putExtra("user", utente);
                    finish();
                    startActivity(dati);
                }

            }
        });

        other_profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", utente);
                intent.putExtra("visitor", new Utente("ElenaMindfulBiz", "", "", R.drawable.user1, new Data()));
                startActivity(intent);
            }
        });

        other_profile_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", utente);
                intent.putExtra("visitor", new Utente("ElenaMindfulBiz", "", "", R.drawable.user1, new Data()));
                startActivity(intent);
            }
        });
    }

    /* Metodo che permette di fare play/pausa agli audio */
    public void gestisciPulsantiAudio (View b, int which) {

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null && !playpausa) {
                    player = MediaPlayer.create(PlaylistActivity.this, which);
                    player.start();
                    playpausa = true;
                } else if (player != null && !playpausa) {
                    player.start();
                    playpausa = true;
                } else if (player != null && playpausa) {
                    player.pause();
                    playpausa = false;
                }

            }
        });
    }
}