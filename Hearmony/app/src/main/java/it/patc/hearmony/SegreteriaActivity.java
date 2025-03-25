package it.patc.hearmony;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.imageview.ShapeableImageView;

import it.patc.hearmony.classes.Data;
import it.patc.hearmony.classes.Playlist;
import it.patc.hearmony.classes.Utente;

public class SegreteriaActivity extends AppCompatActivity {
    /* Definizione Attributi */
    ImageView audio1;
    MediaPlayer player;
    boolean playpausa = false;
    Utente utente;
    TextView h1, descrizione;
    Playlist pl;
    ShapeableImageView other_profile_img;
    TextView other_profile_name;
    ImageButton backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segreteria);

        /* Recupero l'utente e la playlist */
        utente = (Utente) getIntent().getSerializableExtra("user");
        pl = (Playlist) getIntent().getSerializableExtra("playlist");
        other_profile_img = findViewById(R.id.other_profile_img);
        other_profile_name = findViewById(R.id.other_profile_name);
        backarrow = findViewById(R.id.back_arrow_home);

        /* Gestisco le view */
        audio1 = findViewById(R.id.audio1);
        h1 = findViewById(R.id.H1);
        descrizione = findViewById(R.id.descrizione);

        /* Cambio H1 con il nome della playlist */
        h1.setText(pl.getNome());

        /* Cambio la descrizione */
        descrizione.setText(pl.getDescrizione());

        /* Gestisco il pulsante di audio */
        gestisciPulsantiAudio(audio1, R.raw.prova1);

        other_profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", utente);
                intent.putExtra("visitor", new Utente("ElenaMindfulBiz", "", "", R.drawable.user1, new Data()));
                finish();
                startActivity(intent);
            }
        });

        other_profile_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", utente);
                intent.putExtra("visitor", new Utente("ElenaMindfulBiz", "", "", R.drawable.user1, new Data()));
                finish();
                startActivity(intent);
            }
        });

        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
            }
        });

        /* Recupero gli elementi della schermata */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_segreteria);

        /* Gestisco la navbar */
        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_segreteria)
                return true;

            else if(item.getItemId() == R.id.nav_search)
            {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_addaudio)
            {
                Intent intent = new Intent(getApplicationContext(), AddAudioActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_profile)
            {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_home)
            {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            return false;
        });
    }

    public void gestisciPulsantiAudio (View b, int which) {

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null && !playpausa) {
                    player = MediaPlayer.create(SegreteriaActivity.this, which);
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