package it.patc.hearmony;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import it.patc.hearmony.classes.Playlist;
import it.patc.hearmony.classes.Utente;

public class AddAudioActivity extends AppCompatActivity {
    Utente utente;
    ImageButton avanti;
    ImageButton elimina;
    ImageButton riprendi;
    ImageButton stop;
    TextView testo_avanti;
    TextView testo_elimina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_audio);

        utente = (Utente) getIntent().getSerializableExtra("user");

        avanti = findViewById(R.id.avanti);
        elimina = findViewById(R.id.elimina);
        riprendi = findViewById(R.id.riprendi);
        stop = findViewById(R.id.stop);
        testo_avanti = findViewById(R.id.testo_avanti);
        testo_elimina = findViewById(R.id.testo_elimina);

        riprendi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testo_avanti.setVisibility(View.GONE);
                testo_elimina.setVisibility(View.GONE);
                avanti.setVisibility(View.GONE);
                elimina.setVisibility(View.GONE);
                riprendi.setVisibility(View.GONE);
                stop.setVisibility(View.VISIBLE);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testo_avanti.setVisibility(View.VISIBLE);
                testo_elimina.setVisibility(View.VISIBLE);
                avanti.setVisibility(View.VISIBLE);
                elimina.setVisibility(View.VISIBLE);
                stop.setVisibility(View.GONE);
                riprendi.setVisibility(View.VISIBLE);
            }
        });

        avanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), EditAudioActivity.class);
                intent.putExtra("user", utente);
                startActivity(intent);
            }
        });

        elimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alert = new AlertDialog.Builder(AddAudioActivity.this);
                alert.setMessage("Eliminare l'audio?");

                alert.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /* Faccio tornare l'utente alla schermata home */
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        intent.putExtra("user", utente);
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

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav_view);
        bottomNavigationView.setSelectedItemId(R.id.nav_addaudio);

        bottomNavigationView.setOnItemSelectedListener(item ->
        {
            if(item.getItemId() == R.id.nav_addaudio)
                return true;

            else if(item.getItemId() == R.id.nav_search)
            {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
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

            else if(item.getItemId() == R.id.nav_profile)
            {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
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
    }
}