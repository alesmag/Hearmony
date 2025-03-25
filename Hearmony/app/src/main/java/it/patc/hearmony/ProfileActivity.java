/* Definizione Package */
package it.patc.hearmony;

/* Definizione Import */
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.patc.hearmony.classes.Playlist;
import it.patc.hearmony.classes.Utente;

/* Definizione Classe */
public class ProfileActivity extends AppCompatActivity {

    /* Definizione Attributi */
    Utente utente;
    ImageView immagine_profilo, audio1;
    TextView nome_profilo, descrizione_profilo, seguiti, follower, caricamenti;
    ImageButton logout;
    MediaPlayer player;
    boolean playpausa = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /* Recupero le informazioni dell'utente loggato */
        utente = (Utente) getIntent().getSerializableExtra("user");

        /* Recupero le View */
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_profile);
        logout = findViewById(R.id.logout);
        immagine_profilo = findViewById(R.id.immagine_profilo);
        nome_profilo = findViewById(R.id.nome_profilo);
        descrizione_profilo = findViewById(R.id.descrizione_profilo);
        seguiti = findViewById(R.id.seguiti);
        follower = findViewById(R.id.follower);
        caricamenti = findViewById(R.id.caricamenti);
        audio1 = findViewById(R.id.audio1);

        /* Controllo se siamo in modalità visitatore */
        if (getIntent().getSerializableExtra("visitor") != null) {
            Utente visitor = (Utente) getIntent().getSerializableExtra("visitor");
            immagine_profilo.setImageResource(visitor.getImmagine());
            nome_profilo.setText(visitor.getNomeUtente());
            descrizione_profilo.setText(visitor.getDescrizione());
            seguiti.setText(visitor.getSeguiti() + "");
            follower.setText(visitor.getFollower() + "");
            caricamenti.setText(visitor.getCaricamenti() + "");
            logout.setVisibility(View.INVISIBLE);
            AppCompatButton bottone1 = findViewById(R.id.bottone_seguito);
            bottone1.setVisibility(View.GONE);
            AppCompatButton bottone2 = findViewById(R.id.bottone_segui);
            bottone2.setVisibility(View.VISIBLE);

            bottone2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottone2.setVisibility(View.GONE);
                    bottone1.setVisibility(View.VISIBLE);
                    visitor.setFollower(visitor.getFollower()+1);
                    follower.setText(visitor.getFollower() + "");
                }
            });

            bottone1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    bottone1.setVisibility(View.GONE);
                    bottone2.setVisibility(View.VISIBLE);
                    visitor.setFollower(visitor.getFollower()-1);
                    follower.setText(visitor.getFollower() + "");
                }
            });
        } else {
            /* Imposto gli elementi del profilo (immagine, username, descrizione ect...) */
            immagine_profilo.setImageResource(utente.getImmagine());
            nome_profilo.setText(utente.getNomeUtente());
            descrizione_profilo.setText(utente.getDescrizione());
            seguiti.setText(utente.getSeguiti() + "");
            follower.setText(utente.getFollower() + "");
            caricamenti.setText(utente.getCaricamenti() + "");
            View bottoneSeguito = findViewById(R.id.bottone_seguito);
            bottoneSeguito.setVisibility(View.GONE);
        }

        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder alert = new AlertDialog.Builder(ProfileActivity.this);
                alert.setMessage("Vuoi davvero uscire?");

                alert.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // niente
                    }
                });

                alert.create().show();
            }
        });

        /* Gestisco la navbar */
        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_profile) {
                if (getIntent().getSerializableExtra("visitor") != null) {
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                    intent.putExtra("user", utente);
                    finish();
                    startActivity(intent);
                }
                return true;
            }

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

            else if(item.getItemId() == R.id.nav_home)
            {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                intent.putExtra("user", utente);
                finish();
                startActivity(intent);
                return true;
            }

            else if(item.getItemId() == R.id.nav_segreteria)
            {
                Playlist segreteria = new Playlist("Segreteria", "Audio inviati dai tuoi amici", 0);
                Intent intent = new Intent(getApplicationContext(), SegreteriaActivity.class);
                intent.putExtra("user", utente);
                intent.putExtra("playlist", segreteria);
                finish();
                startActivity(intent);
                return true;
            }

            return false;
        });

        /* Gestisco l'audio */
        audio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null && !playpausa) {
                    player = MediaPlayer.create(ProfileActivity.this, R.raw.prova1);
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